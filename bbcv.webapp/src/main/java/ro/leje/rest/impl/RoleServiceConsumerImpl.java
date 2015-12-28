package ro.leje.rest.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.leje.config.ServiceSettings;
import ro.leje.model.vo.Role;
import ro.leje.rest.RoleServiceConsumer;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Danut Chindris
 * @since September 14, 2015
 */
@Service
public class RoleServiceConsumerImpl implements RoleServiceConsumer {

    @Resource
    private ServiceSettings serviceSettings;

    @Override
    public List<Role> findAll() {
        String endpoint = serviceSettings.getRoles();
        RestTemplate restTemplate = new RestTemplate();
        // http://thespringway.info/spring-web/map-to-list-of-objects-from-json-array-with-resttemplate/
        ParameterizedTypeReference<List<Role>> responseType = new ParameterizedTypeReference<List<Role>>() {};
        ResponseEntity<List<Role>> result = restTemplate.exchange(endpoint,
                HttpMethod.GET, null, responseType);
        List<Role> list = result.getBody();
        return list != null ? list : Collections.emptyList();
    }
}
