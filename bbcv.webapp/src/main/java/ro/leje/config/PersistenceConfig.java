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
@ComponentScan({ PersistenceConfig.BASE_ENTITY_PACKAGE })
public class PersistenceConfig {

    static final String BASE_ENTITY_PACKAGE = "ro.leje.model.entity";

    private static final String CHARACTER_ENCODING_CONNECTION_PROPERTY = "characterEncoding";
    private static final String CHARACTER_ENCODING_UTF_8 = "UTF-8";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_CONNECTION_CHARACTER_ENCODING = "hibernate.connection.characterEncoding";

    @Resource
    private Settings settings;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { BASE_ENTITY_PACKAGE });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(settings.getDriver());
        dataSource.setUrl(settings.getUrl());
        dataSource.setUsername(settings.getUsr());
        dataSource.setPassword(settings.getPassword());
        dataSource.addConnectionProperty(CHARACTER_ENCODING_CONNECTION_PROPERTY, CHARACTER_ENCODING_UTF_8);

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
                setProperty(HIBERNATE_DIALECT, settings.getDialect());
                setProperty(HIBERNATE_SHOW_SQL, settings.getShowSql());
                setProperty(HIBERNATE_CONNECTION_CHARACTER_ENCODING, settings.getCharacterEncoding());
            }
        };
    }
}
