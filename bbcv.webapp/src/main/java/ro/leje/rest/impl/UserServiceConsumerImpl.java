package ro.leje.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.leje.config.ServiceSettings;
import ro.leje.model.vo.User;
import ro.leje.rest.UserServiceConsumer;
import ro.leje.util.RestMappings;

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

    private static final String PARAM_USER_NAME = "userName";
    @Autowired
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
        String endpoint = serviceSettings.getUser() + RestMappings.USER_FIND_ALL;
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
        return null;
    }
}
