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

    private String role;

    private String link;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
