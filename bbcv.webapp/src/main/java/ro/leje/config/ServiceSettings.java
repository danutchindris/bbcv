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

    private String users;

    private String roles;

    private String links;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
