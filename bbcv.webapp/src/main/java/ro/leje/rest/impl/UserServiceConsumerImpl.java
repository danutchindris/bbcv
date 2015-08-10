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

    @Autowired
    private ServiceSettings serviceSettings;

    @Override
    public List<User> getUsersByFirstName(String firstName) {
        String endpoint = serviceSettings.getUser() + "/{firstName}";
        Map<String, String> params = new HashMap<>();
        params.put("firstName", firstName);
        RestTemplate restTemplate = new RestTemplate();
        // http://thespringway.info/spring-web/map-to-list-of-objects-from-json-array-with-resttemplate/
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> result = restTemplate.exchange(endpoint,
                HttpMethod.GET, null, responseType, params);
        List<User> list = result.getBody();
        return list != null ? list : Collections.emptyList();
    }
}
