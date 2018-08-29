package nc.unc.importparcoursup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import nc.unc.importparcoursup.dao.leptoDAO.ActivitesTypeContratRepository;
import nc.unc.importparcoursup.dao.parcoursupDAO.StudentRepository;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ActivitesTypeContratRepository activiteRepo;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
	log.info("Test repo parcoursup (nb students): " + studentRepo.count());
	log.info("Test repo leptop (nb activité):  " + activiteRepo.count());
    }

}
