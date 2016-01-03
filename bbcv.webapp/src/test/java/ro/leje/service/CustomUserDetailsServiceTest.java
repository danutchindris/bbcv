package ro.leje.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import ro.leje.AbstractTest;
import ro.leje.model.CustomUserDetails;

import javax.annotation.Resource;

/**
 * @author Danut Chindris
 * @since August 14, 2015
 */
public class CustomUserDetailsServiceTest extends AbstractTest {

    private final static long TEST_USER_ID = 1L;
    private final static String TEST_USER_USERNAME = "test.user";
    private final static String TEST_USER_FIRSTNAME = "Test";
    private final static String TEST_USER_LASTNAME = "User";
    private final static String TEST_USER_EMAIL = "test.user@testuser.app";

    private final static String TEST_USER_AUTHORITY = "admin";

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void whenCorrectUserNameIsProvidedGetCustomUserDetailsByUserNameReturnsNotNull() {
        CustomUserDetails customUserDetails = customUserDetailsService.getCustomUserDetailsByUserName(TEST_USER_USERNAME);
        Assert.assertNotNull("The returned custom user details shouldn't be null", customUserDetails);
        Assert.assertEquals("The custom user details' id is incorrect", TEST_USER_ID, customUserDetails.getId());
        Assert.assertEquals("The custom user details' user name is incorrect", TEST_USER_USERNAME, customUserDetails.getUserName());
        Assert.assertEquals("The custom user details' first name is incorrect", TEST_USER_FIRSTNAME, customUserDetails.getFirstName());
        Assert.assertEquals("The custom user details' last name is incorrect", TEST_USER_LASTNAME, customUserDetails.getLastName());
        Assert.assertEquals("The custom user details' email is incorrect", TEST_USER_EMAIL, customUserDetails.getEmail());
    }

    @Test
    public void whenCorrectUserNameIsProvidedLoadUserByUsernameReturnsNotNull() {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(TEST_USER_USERNAME);
        Assert.assertNotNull("The custom user details object shouldn't be null", userDetails);
        Assert.assertEquals("The user name is incorrect", TEST_USER_USERNAME, userDetails.getUsername());
        Assert.assertNotNull("The password shouldn't be null", userDetails.getPassword());
        Assert.assertTrue("The authorities list should contain four elements",
                (userDetails.getAuthorities() != null && userDetails.getAuthorities().size() == 4));
        Assert.assertEquals("The test user's first authority is incorrect", TEST_USER_AUTHORITY,
                userDetails.getAuthorities().iterator().next().getAuthority());
        Assert.assertEquals("The account shouldn't be expired", true, userDetails.isAccountNonExpired());
        Assert.assertEquals("The account shouldn't be locked", true, userDetails.isAccountNonLocked());
        Assert.assertEquals("The credentials shouldn't be expired", true, userDetails.isCredentialsNonExpired());
        Assert.assertEquals("The user should be enabled", true, userDetails.isEnabled());
    }
}
