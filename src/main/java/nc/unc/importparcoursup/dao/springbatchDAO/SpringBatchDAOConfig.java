package nc.unc.importparcoursup.dao.springbatchDAO;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "sbEntityManagerFactory", transactionManagerRef = "sbTransactionManager")
public class SpringBatchDAOConfig {

    @Primary
    @Bean
    PlatformTransactionManager sbTransactionManager() {
	return new JpaTransactionManager(sbEntityManagerFactory().getObject());
    }

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean sbEntityManagerFactory() {

	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	vendorAdapter.setGenerateDdl(true);

	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

	factoryBean.setDataSource(orderDataSource());
	factoryBean.setJpaVendorAdapter(vendorAdapter);
	factoryBean.setPackagesToScan(SpringBatchDAOConfig.class.getPackage()
		.getName());
	return factoryBean;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasource.springbatch")
    DataSource orderDataSource() {
	return DataSourceBuilder.create()
		.build();
    }
}