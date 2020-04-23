//package hello.configuration;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//public class PersistenceContext {
//
//    @Autowired
//    private Environment environment;
//
//    @Bean(name = "sessionFactory")
//    public SessionFactory sessionFactory() throws IOException {
//        final LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
//        factory.setDataSource(dataSource());
//        factory.setHibernateProperties(hibernateProperties());
//        factory.setPackagesToScan("com.idvsbruck.test.entity");
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        final BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl(environment.getProperty("database.url"));
//        dataSource.setUsername(environment.getProperty("database.user"));
//        dataSource.setPassword(environment.getProperty("database.password"));
//        return dataSource;
//    }
//
//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager transactionManager() throws IOException {
//        return new HibernateTransactionManager(sessionFactory());
//    }
//
//    private static Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        hibernateProperties.put("hibernate.connection.autocommit", true);
//        hibernateProperties.put("hibernate.show_sql", false);
//        hibernateProperties.put("hibernate.format_sql", false);
//        hibernateProperties.put("hibernate.generate_statistics", false);
//        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
//        hibernateProperties.put("hibernate.use_sql_comments", false);
//        hibernateProperties.put("hibernate.cache.use_query_cache", false);
//        hibernateProperties.put("hibernate.cache.use_second_level_cache", true);
//        return hibernateProperties;
//    }
//}
