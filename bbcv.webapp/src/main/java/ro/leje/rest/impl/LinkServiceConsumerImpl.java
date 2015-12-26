package ro.leje.rest.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.leje.config.ServiceSettings;
import ro.leje.model.vo.Link;
import ro.leje.rest.LinkServiceConsumer;
import ro.leje.util.RestMappings;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author Danut Chindris
 * @since December 6, 2015
 */
@Service
public class LinkServiceConsumerImpl implements LinkServiceConsumer {

    @Resource
    private ServiceSettings serviceSettings;

    @Override
    public List<Link> findAll() {
        String endpoint = serviceSettings.getLink() + RestMappings.FIND_ALL;
        RestTemplate restTemplate = new RestTemplate();
        // http://thespringway.info/spring-web/map-to-list-of-objects-from-json-array-with-resttemplate/
        ParameterizedTypeReference<List<Link>> responseType = new ParameterizedTypeReference<List<Link>>() {};
        ResponseEntity<List<Link>> result = restTemplate.exchange(endpoint,
                HttpMethod.GET, null, responseType);
        List<Link> list = result.getBody();
        return list != null ? list : Collections.emptyList();
    }
}
