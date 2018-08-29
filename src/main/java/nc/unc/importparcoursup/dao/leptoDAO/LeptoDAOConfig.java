package nc.unc.importparcoursup.dao.leptoDAO;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "leptoEntityManagerFactory", transactionManagerRef = "leptoTransactionManager")
public class LeptoDAOConfig {

    @Bean
    public PlatformTransactionManager leptoTransactionManager() {
	return new JpaTransactionManager(leptoEntityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean leptoEntityManagerFactory() {
	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	factoryBean.setDataSource(leptoDataSource());
	factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	factoryBean.setPackagesToScan(LeptoDAOConfig.class.getPackage().getName());
	return factoryBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.lepto")
    public DataSource leptoDataSource() {
	return DataSourceBuilder.create().build();
    }

}