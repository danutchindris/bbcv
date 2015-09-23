package ro.leje.rest.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.leje.config.ServiceSettings;
import ro.leje.model.vo.Permission;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;
import ro.leje.rest.UserServiceConsumer;
import ro.leje.util.RestMappings;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Danut Chindris
 * @since July 5, 2015
 */
@Service
public class UserServiceConsumerImpl implements UserServiceConsumer {

    private static final String PARAM_ID = "id";
    private static final String PARAM_USER_NAME = "userName";
    private static final String PARAM_USER_ID = "userId";
    private static final String PARAM_ROLE_ID = "roleId";

    @Resource
    private ServiceSettings serviceSettings;

    @Override
    public User findByUserName(String userName) {
        String endpoint = serviceSettings.getUser() + RestMappings.USER_FIND_BY_USER_NAME;
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_USER_NAME, userName);
        RestTemplate restTemplate = new RestTemplate();
        User object = restTemplate.getForObject(endpoint, User.class, params);
        return object != null ? object : new User();
    }

    @Override
    public List<User> findAll() {
        String endpoint = serviceSettings.getUser() + RestMappings.FIND_ALL;
        RestTemplate restTemplate = new RestTemplate();
        // http://thespringway.info/spring-web/map-to-list-of-objects-from-json-array-with-resttemplate/
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> result = restTemplate.exchange(endpoint,
                HttpMethod.GET, null, responseType);
        List<User> list = result.getBody();
        return list != null ? list : Collections.emptyList();
    }

    @Override
    public Long create(User user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(serviceSettings.getUser(), user, Long.class);
    }

    @Override
    public List<Role> findRoles(long userId) {
        String endpoint = serviceSettings.getUser() + RestMappings.USER_FIND_ROLES;
        Map<String, Long> params = new HashMap<>();
        params.put(PARAM_USER_ID, userId);
        RestTemplate restTemplate = new RestTemplate();
        // http://thespringway.info/spring-web/map-to-list-of-objects-from-json-array-with-resttemplate/
        ParameterizedTypeReference<List<Role>> responseType = new ParameterizedTypeReference<List<Role>>() {};
        ResponseEntity<List<Role>> result = restTemplate.exchange(endpoint,
                HttpMethod.GET, null, responseType, params);
        List<Role> list = result.getBody();
        return list != null ? list : Collections.emptyList();
    }

    @Override
    public boolean addRole(long userId, long roleId) {
        String endpoint = serviceSettings.getUser() + RestMappings.USER_ADD_ROLE;
        Map<String, Long> params = new HashMap<>();
        params.put(PARAM_USER_ID, userId);
        params.put(PARAM_ROLE_ID, roleId);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(endpoint, null, Boolean.class, params);
    }

    @Override
    public List<Permission> findPermissions(long userId) {
        String endpoint = serviceSettings.getUser() + RestMappings.USER_FIND_PERMISSIONS;
        Map<String, Long> params = new HashMap<>();
        params.put(PARAM_USER_ID, userId);
        RestTemplate restTemplate = new RestTemplate();
        // http://thespringway.info/spring-web/map-to-list-of-objects-from-json-array-with-resttemplate/
        ParameterizedTypeReference<List<Permission>> responseType = new ParameterizedTypeReference<List<Permission>>() {};
        ResponseEntity<List<Permission>> result = restTemplate.exchange(endpoint,
                HttpMethod.GET, null, responseType, params);
        List<Permission> list = result.getBody();
        return list != null ? list : Collections.emptyList();
    }
}
