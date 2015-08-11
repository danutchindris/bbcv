package ro.leje.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.leje.config.ServiceSettings;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.User;
import ro.leje.service.CustomUserDetailsService;

import java.util.*;

/**
 * @author Danut Chindris
 * @since August 11, 2015
 */
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private ServiceSettings serviceSettings;

    public CustomUserDetails getCustomUserDetailsByUserName(String userName) {
        String endpoint = serviceSettings.getUser() + "/userName/{userName}";
        Map<String, String> params = new HashMap<>();
        params.put("userName", userName);
        RestTemplate restTemplate = new RestTemplate();
        // http://thespringway.info/spring-web/map-to-list-of-objects-from-json-array-with-resttemplate/
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};
        ResponseEntity<List<User>> result = restTemplate.exchange(endpoint,
                HttpMethod.GET, null, responseType, params);
        List<User> list = result.getBody();
        //TODO finish implementation
        User user = (list != null && !list.isEmpty()) ? list.get(0) : null;
        CustomUserDetails customUserDetails = new CustomUserDetails();
        return customUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            //TODO dummy role added temporarily
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            authorities.add(authority);
            CustomUserDetails customUserDetails = getCustomUserDetailsByUserName(userName);
            customUserDetails.setAuthorities(authorities);
            return customUserDetails;
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException("Could not retrieve user", e);
        }
    }
}
