package ro.leje.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ro.leje.model.CustomUserDetails;

/**
 * @author Danut Chindris
 * @since August 11, 2015
 */
public interface CustomUserDetailsService extends UserDetailsService {

    CustomUserDetails getCustomUserDetailsByUserName(String userName);
}
