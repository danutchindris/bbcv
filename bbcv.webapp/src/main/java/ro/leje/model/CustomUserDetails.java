package ro.leje.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.leje.model.vo.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Danut Chindris
 * @since August 11, 2015
 */
public class CustomUserDetails extends User implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //TODO temporary authorities implementation
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO provide implementation
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO provide implementation
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO provide implementation
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getEnabled();
    }
}
