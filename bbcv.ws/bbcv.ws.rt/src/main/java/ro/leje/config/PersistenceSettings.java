package ro.leje.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * A class that holds several fields representing the connections and configuration
 * details for properly talking to the database.
 *
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="jdbc, hibernate")
public class PersistenceSettings {

    private String driverClassName;
    private String url;
    private String user;
    private String password;
}
