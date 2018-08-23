package nc.unc.parcoursupui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nc.unc.parcoursupui.student.repositories.StudentRepository;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final StudentRepository studentRepo;

    @Autowired
    public JobCompletionNotificationListener(StudentRepository studentRepo) {
	this.studentRepo = studentRepo;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
	if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
	    log.info("!!! JOB TERMINE! -> Nombre de lignes traitées: " + studentRepo.count());
	    
	}
    }
}
