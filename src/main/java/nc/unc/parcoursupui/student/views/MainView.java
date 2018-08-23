package nc.unc.parcoursupui.student.views;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;

import nc.unc.parcoursupui.student.repositories.StudentHistoryRepository;
import nc.unc.parcoursupui.student.repositories.StudentRepository;

/**
 * The main view contains a simple label element and a template element.
 */
@HtmlImport("@vaadin/vaadin-upload/vaadin-upload.js")
@HtmlImport("@vaadin/vaadin-button/vaadin-button.js")
@HtmlImport("@vaadin/vaadin-grid/vaadin-grid.js")
@Route
@Push
public class MainView extends VerticalLayout {
    static final long serialVersionUID = 1L;
        
    Button goButton;
    UploadComponent myUploadComponent;
    InformationComponent infoComponent;
    
    public MainView(@Autowired StudentHistoryRepository studentHistoryRepository, @Autowired StudentRepository studentRepo, @Autowired Job jobImportCsv, @Autowired JobLauncher jobLauncher, @Value("${file.local-tmp-file}") String inputFile) {		
	this.setAlignItems(Alignment.CENTER);
	myUploadComponent = new UploadComponent(inputFile);
	myUploadComponent.addSucceededListener(event -> uploadFileSuccceed ());
	add(myUploadComponent);
	
	goButton = new Button("Importer le fichier", event -> importFile(studentRepo, jobImportCsv, jobLauncher));
	add(goButton);
	
	infoComponent = new InformationComponent(studentRepo, studentHistoryRepository);
	add(infoComponent);	
    }

    private void uploadFileSuccceed() {
	infoComponent.update(UI.getCurrent(), myUploadComponent.getFile());
	
    }

    private void importFile(StudentRepository studentRepo, Job jobImportCsv, JobLauncher jobLauncher) {
	try {
	    File fileToImport = myUploadComponent.getFile();		    
	    if (fileToImport != null) {
		jobLauncher.run(jobImportCsv, new JobParametersBuilder().addString("fileName", fileToImport.getAbsolutePath()).addLong("uniqueness", System.nanoTime()).toJobParameters());
	    } else {
		Notification.show("Veuillez sélectionner un fichier à traiter.");
	    }
	} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
	    throw new RuntimeException(e);
	}
	uploadFileSuccceed();
    }
}
