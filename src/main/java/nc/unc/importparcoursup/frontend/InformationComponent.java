package nc.unc.importparcoursup.frontend;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import nc.unc.importparcoursup.commons.model.StudentHistory;
import nc.unc.importparcoursup.commons.repositories.StudentHistoryRepository;
import nc.unc.importparcoursup.commons.repositories.StudentRepository;

public class InformationComponent extends HorizontalLayout {

    StudentRepository studentRepo;
    StudentHistoryRepository studentHistoryRepository;

    Label nbLineInFile = new Label();
    Label nbColumnInFile = new Label();
    Label nbLineInDB = new Label();
    Label lastExecutionDate = new Label();
    Label lastExecutionNbLines = new Label();

    VerticalLayout componentLeft = new VerticalLayout();
    VerticalLayout componentRight = new VerticalLayout();    
 
    public InformationComponent(StudentRepository studentRepo, StudentHistoryRepository studentHistoryRepository) {
	this.studentRepo = studentRepo;
	this.studentHistoryRepository = studentHistoryRepository;
	
	// style
	this.setWidth("80%");
	this.getStyle().set("border", "1px solid #9E9E9E");
	add(componentLeft, componentRight);
	
	addLine("Fichier - Lignes", nbLineInFile);
	addLine("Fichier - Colonnes", nbColumnInFile);
	addLine("Dernier Job - Date", lastExecutionDate);
	addLine("Dernier Job - Lignes importées", lastExecutionNbLines);
	addLine("Base - Lignes", nbLineInDB);
	
    }

    private void addLine(String label, Label... value) {
	componentLeft.add(new Label(label));
	componentRight.add(value);
    }

    public void update(File file) {
	StudentHistory lastHisto = studentHistoryRepository.findFirstByOrderByJobExecutionDateDesc();
	nbLineInFile.setText(getNbLines(file));
	nbLineInDB.setText("" + studentRepo.count());
	lastExecutionDate.setText(getLastExecDate(lastHisto));
	lastExecutionNbLines.setText(getLastExecNbLines(lastHisto));
	nbColumnInFile.setText(getNbColumnInFile(file));
    }

    private String getNbLines(File file) {
	
	try {
	    // on fait -1 car il y a une ligne d'entete
	    String nbLines = file !=null?""+(Files.lines(file.toPath(), Charset.defaultCharset()).count()-1):"-";
	    return nbLines;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
    
    private String getLastExecDate(StudentHistory lastHisto) {	
	return lastHisto != null ? ""+lastHisto.getJobExecutionDate() : "-";
    }
    
    private String getLastExecNbLines(StudentHistory lastHisto) {
	return lastHisto != null ? ""+lastHisto.getStudents().size() : "-";
    }
    
    private String getNbColumnInFile(File file) {
	if (file != null) {
	    try {
		CSVReaderBuilder builder;
		builder = new CSVReaderBuilder(new FileReader(file));
		CSVParser parser = new CSVParserBuilder().withSeparator('\t').build();
		CSVReader reader = builder.withCSVParser(parser).build();
		return "" + reader.readNext().length;
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
	return "-";
    }
}
