package nc.unc.importparcoursup.dao.leptoDAO;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "LeptoManagerFactory", basePackages = { "nc.unc.importparcoursup.dao.leptoDAO" })
public class LeptoDAOConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix = "datasource.lepto")
//    public DataSource dataSource() {
//	return DataSourceBuilder.create()
//		.build();
//    }
//
//    @Bean(name = "LeptoManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
//	LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource)
//		.packages("nc.unc.importparcoursup.commons")
//		.build();
//
//	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	em.setJpaVendorAdapter(vendorAdapter);
//	HashMap<String, Object> properties = new HashMap<>();
//	properties.put("hibernate.hbm2ddl.auto", "update");
//	properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
//	em.setJpaPropertyMap(properties);
//
//	return em;
//    }
//
//    @Bean(name = "transactionManager")
//    public JpaTransactionManager transactionManager(@Qualifier("LeptoManagerFactory") EntityManagerFactory entityManagerFactory) {
//	return new JpaTransactionManager(entityManagerFactory);
//    }

}