package nc.unc.parcoursupui.student.views;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import nc.unc.parcoursupui.student.repositories.StudentHistoryRepository;
import nc.unc.parcoursupui.student.repositories.StudentRepository;

public class InformationComponent extends HorizontalLayout {

    StudentRepository studentRepo;
    StudentHistoryRepository studentHistoryRepository;

    Label nbLineInFile = new Label();
    Label nbLineInDB = new Label();
    Label lastExecutionDate = new Label();

    VerticalLayout componentLeft = new VerticalLayout();
    VerticalLayout componentRight = new VerticalLayout();
 
    public InformationComponent(StudentRepository studentRepo, StudentHistoryRepository studentHistoryRepository) {
	this.studentRepo = studentRepo;
	this.studentHistoryRepository = studentHistoryRepository;

	// style
	this.setWidth("25%");
	this.setAlignItems(Alignment.CENTER);
	this.getStyle().set("border", "1px solid #9E9E9E");
	setFlexGrow(1, componentLeft);
	setFlexGrow(1, componentRight);
	add(componentLeft, componentRight);

	addLine("Nombre de lignes dans le fichier", nbLineInFile);
	addLine("Nombre de lignes dans la base", nbLineInDB);
	addLine("Dernière éxécution", lastExecutionDate);
    }

    private void addLine(String label, Label value) {
	componentLeft.add(new Label(label));
	componentRight.add(value);
    }

    public void update(UI ui, File file) {
	try {
	    
	    String nbLines = file !=null?""+Files.lines(file.toPath(), Charset.defaultCharset()).count():"-";
	    
	    ui.access(() ->
		{
		    nbLineInFile.setText(nbLines);
		    nbLineInDB.setText("-" + studentRepo.count());
		    System.out.println(" => " + "-"+studentHistoryRepository.findFirstByOrderByJobExecutionDateDesc().getJobExecutionDate());
		    lastExecutionDate.setText("-"+studentHistoryRepository.findFirstByOrderByJobExecutionDateDesc().getJobExecutionDate());
		});
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
}
