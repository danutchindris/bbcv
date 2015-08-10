package ro.leje.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Danut Chindris
 * @since July 29, 2015
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="ro.leje.services")
public class ServiceSettings {

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
