package nc.unc.importparcoursup.dao2;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "barEntityManagerFactory", 
        transactionManagerRef = "barTransactionManager",
        basePackages = { "nc.unc.importparcoursup.dao2" })
public class BarConfig {

    @Bean
    @ConfigurationProperties(prefix="parcoursup.datasource")
    public DataSource barDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "barEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("barDataSource") DataSource barDataSource) {
        return builder
                .dataSource(barDataSource)
                .packages("nc.unc.importparcoursup.dao2")
                .persistenceUnit("barz")
                .build();
    }

    @Bean(name = "barTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

}