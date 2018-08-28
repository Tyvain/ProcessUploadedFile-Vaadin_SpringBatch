package nc.unc.importparcoursup.dao;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = { "nc.unc.importparcoursup.dao.repositories" })
public class FooConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "parcoursup.datasource")
    public DataSource dataSource() {
	return DataSourceBuilder.create()
		.build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
	LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource)
		.packages("nc.unc.importparcoursup.commons")
		.persistenceUnit("studentz")
		.build();

	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	em.setJpaVendorAdapter(vendorAdapter);
	HashMap<String, Object> properties = new HashMap<>();
	properties.put("hibernate.hbm2ddl.auto", "create-drop");
	properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
	em.setJpaPropertyMap(properties);

	return em;
    }

    @Primary
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
	return new JpaTransactionManager(entityManagerFactory);
    }

}