package ro.leje.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Permission;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Danut Chindris
 * @since August 11, 2015
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private static final String USER_NAME_PARAM = "userName";

    @Resource
    private UserService userService;

    @Override
    public CustomUserDetails getCustomUserDetailsByUserName(String userName) {
        User user = userService.findByUserName(userName);
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setUserName(user.getUserName());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setFirstName(user.getFirstName());
        customUserDetails.setLastName(user.getLastName());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setEnabled(user.getEnabled());
        return customUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            CustomUserDetails customUserDetails = getCustomUserDetailsByUserName(userName);
            List<Role> roles = userService.findRoles(customUserDetails.getId());
            List<Permission> permissions = userService.findPermissions(customUserDetails.getId());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority;
            for (Role role : roles) {
                authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
            for (Permission permission : permissions) {
                authority = new SimpleGrantedAuthority(permission.getName());
                authorities.add(authority);
            }
            customUserDetails.setAuthorities(authorities);
            return customUserDetails;
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException("Could not retrieve user", e);
        }
    }
}
