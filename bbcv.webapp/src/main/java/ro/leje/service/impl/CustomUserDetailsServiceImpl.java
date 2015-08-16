package ro.leje.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Danut Chindris
 * @since August 11, 2015
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private static final String FIND_BY_USER_NAME_URL_SUFFIX = "/findByUserName/{userName}";
    private static final String USER_NAME_PARAM = "userName";

    @Autowired
    private ServiceSettings serviceSettings;

    public CustomUserDetails getCustomUserDetailsByUserName(String userName) {
        String endpoint = serviceSettings.getUser() + FIND_BY_USER_NAME_URL_SUFFIX;
        Map<String, String> params = new HashMap<>();
        params.put(USER_NAME_PARAM, userName);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(endpoint, User.class, params);
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setUserName(user.getUserName());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setFirstName(user.getFirstName());
        customUserDetails.setLastName(user.getLastName());
        customUserDetails.setEmail(user.getEmail());
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
