package nc.unc.importparcoursup.view;

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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import nc.unc.importparcoursup.dao.parcoursupDAO.StudentHistoryRepository;
import nc.unc.importparcoursup.dao.parcoursupDAO.StudentRepository;

/**
 * The main view contains a simple label element and a template element.
 */
@HtmlImport("@vaadin/vaadin-upload/vaadin-upload.js")
@HtmlImport("@vaadin/vaadin-button/vaadin-button.js")
@HtmlImport("@vaadin/vaadin-grid/vaadin-grid.js")
@HtmlImport("@vaadin/vaadin-icons/vaadin-icons.js")
@Route
public class MainView extends VerticalLayout {
    static final long serialVersionUID = 1L;

    Button goButton;
    UploadComponent myUploadComponent;
    InformationComponent infoComponent;

    public MainView(@Autowired StudentHistoryRepository studentHistoryRepository, @Autowired StudentRepository studentRepo, @Autowired Job jobImportCsv, @Autowired JobLauncher jobLauncher, @Value("${file.local-tmp-file}") String inputFile) {

	// login
	add(buildLoginPart());

	// upload
	add(buildUploadComponent(studentRepo, jobImportCsv, jobLauncher, inputFile));

	// info
	infoComponent = new InformationComponent(studentRepo, studentHistoryRepository);
	add(infoComponent);
	updateInformationComponent();
    }

    private FormLayout buildLoginPart() {
	FormLayout fl = new FormLayout();
	TextField login = new TextField();
	PasswordField pwd = new PasswordField();
	Button loginBtn = new Button("Login", new Icon(VaadinIcon.SIGN_IN));

	fl.add(login, pwd, loginBtn);
	fl.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("0", 2), new ResponsiveStep("0", 3), new ResponsiveStep("0", 4), new ResponsiveStep("0", 5), new ResponsiveStep("0", 6), new ResponsiveStep("0", 7), new ResponsiveStep("0", 8));
//	fl.getStyle().set("backgroundColor", "#78909C");
	fl.setWidth("100%");
	return fl;
    }

    private Component buildUploadComponent(StudentRepository studentRepo, Job jobImportCsv, JobLauncher jobLauncher, String inputFile) {
	HorizontalLayout uploadComponentContainer = new HorizontalLayout();
	uploadComponentContainer.setAlignItems(Alignment.BASELINE);
	myUploadComponent = new UploadComponent(inputFile);
	myUploadComponent.addSucceededListener(event -> updateInformationComponent());

	add(myUploadComponent);

	goButton = new Button("Importer le fichier", new Icon(VaadinIcon.PLAY), event -> importFile(studentRepo, jobImportCsv, jobLauncher));
	uploadComponentContainer.add(myUploadComponent, goButton);
	return uploadComponentContainer;
    }

    private void updateInformationComponent() {
	infoComponent.update(myUploadComponent.getFile());
	goButton.setEnabled(myUploadComponent.getFile() != null);
    }

    private void importFile(StudentRepository studentRepo, Job jobImportCsv, JobLauncher jobLauncher) {
	try {
	    File fileToImport = myUploadComponent.getFile();
	    if (fileToImport != null) {
		jobLauncher.run(jobImportCsv, new JobParametersBuilder().addString("fileName", fileToImport.getAbsolutePath())
			.addLong("uniqueness", System.nanoTime())
			.toJobParameters());
	    } else {
		Notification.show("Veuillez sélectionner un fichier à traiter.");
	    }
	} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
	    throw new RuntimeException(e);
	}
	updateInformationComponent();
    }
}
