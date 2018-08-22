package nc.unc.parcoursupui.student.view;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import nc.unc.parcoursupui.student.model.StudentRepository;

public class InformationComponent extends HorizontalLayout {

    StudentRepository studentRepo;

    Label nbLineInFile = new Label();
    Label nbLineInDB = new Label();

    VerticalLayout componentLeft = new VerticalLayout();;
    VerticalLayout componentRight = new VerticalLayout();;

    public InformationComponent(StudentRepository studentRepo) {
	this.studentRepo = studentRepo;

	// style
	this.setWidth("25%");
	this.setAlignItems(Alignment.CENTER);
	this.getStyle()
		.set("border", "1px solid #9E9E9E");
	setFlexGrow(1, componentLeft);
	setFlexGrow(1, componentRight);
	add(componentLeft, componentLeft);

	addLine("Nombre de lignes dans le fichier", nbLineInFile);
	addLine("Nombre de lignes dans la base", nbLineInDB);
    }

    private void addLine(String label, Label value) {
	componentLeft.add(new Label(label));
	componentRight.add(value);
    }

    public void update(File file) {
	try {

	    long nbLines = Files.lines(file.toPath(), Charset.defaultCharset()).count();
	    
	    UI.getCurrent().access(() ->
		{
		    System.out.println("UPDATED! " + nbLines);
		    nbLineInFile.setText("1234" + nbLines);
		    nbLineInDB.setText("" + studentRepo.count());
		});
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
}
