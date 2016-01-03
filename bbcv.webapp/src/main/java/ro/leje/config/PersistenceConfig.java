package ro.leje.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "ro.leje.model.entity" })
public class PersistenceConfig {

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_CONNECTION_CHARACTER_ENCODING = "hibernate.connection.characterEncoding";

    @Resource
    private PersistenceSettings persistenceSettings;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] {"ro.leje.model.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(persistenceSettings.getDriver());
        dataSource.setUrl(persistenceSettings.getUrl());
        dataSource.setUsername(persistenceSettings.getUsr());
        dataSource.setPassword(persistenceSettings.getPassword());

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty(HIBERNATE_DIALECT, persistenceSettings.getDialect());
                setProperty(HIBERNATE_SHOW_SQL, persistenceSettings.getShowSql());
                setProperty(HIBERNATE_CONNECTION_CHARACTER_ENCODING, persistenceSettings.getCharacterEncoding());
            }
        };
    }
}
