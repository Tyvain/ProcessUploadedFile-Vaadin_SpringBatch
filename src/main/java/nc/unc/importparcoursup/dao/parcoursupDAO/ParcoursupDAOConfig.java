package nc.unc.importparcoursup.dao.parcoursupDAO;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
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
@EnableJpaRepositories(entityManagerFactoryRef = "parcoursupEntityManagerFactory", transactionManagerRef = "parcoursupTransactionManager")
public class ParcoursupDAOConfig {

    @Value("${datasource.parcoursup.hibernate-hbm2ddl-auto}")
    private String ddlMode;
    
    @Bean
    public PlatformTransactionManager parcoursupTransactionManager() {
	return new JpaTransactionManager(parcoursupEntityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean parcoursupEntityManagerFactory() {
	HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

	HashMap<String, Object> properties = new HashMap<>();
	properties.put("hibernate.hbm2ddl.auto", ddlMode);
	properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");

	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

	factoryBean.setDataSource(parcoursupDataSource());
	factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
	factoryBean.setPackagesToScan(ParcoursupDAOConfig.class.getPackage().getName());
	factoryBean.setJpaPropertyMap(properties);

	return factoryBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.parcoursup")
    public DataSource parcoursupDataSource() {
	return DataSourceBuilder.create()
		.build();
    }
}