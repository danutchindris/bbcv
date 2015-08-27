package ro.leje.rest;

import org.junit.Assert;
import org.junit.Test;
import ro.leje.AbstractWebAppTest;
import ro.leje.model.vo.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 27, 2015
 */
public class UserServiceConsumerTest extends AbstractWebAppTest {

    private final static long TEST_USER_ID = 1L;
    private final static String TEST_USER_USERNAME = "test.user";
    private final static String TEST_USER_FIRSTNAME = "Test";
    private final static String TEST_USER_LASTNAME = "User";
    private final static String TEST_USER_EMAIL = "test.user@testuser.app";

    @Resource
    private UserServiceConsumer userServiceConsumer;

    @Test
    public void whenCalledGetAllUsersReturnsANotEmptyList() {
        List<User> list = userServiceConsumer.getAllUsers();
        Assert.assertNotNull("The users list shouldn't be null", list);
        Assert.assertTrue("The users list should contain one item", list.size() == 1);
    }

    @Test
    public void whenCalledGetAllUsersReturnsAListHavingTheExpectedFirstItem() {
        List<User> list = userServiceConsumer.getAllUsers();
        User object = list.get(0);
        checkUserObjectProperties(object);
    }

    private void checkUserObjectProperties(User object) {
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The id is incorrect", TEST_USER_ID, object.getId());
        Assert.assertEquals("The user name is incorrect", TEST_USER_USERNAME, object.getUserName());
        Assert.assertEquals("The first name is incorrect", TEST_USER_FIRSTNAME, object.getFirstName());
        Assert.assertEquals("The last name is incorrect", TEST_USER_LASTNAME, object.getLastName());
        Assert.assertEquals("The email is incorrect", TEST_USER_EMAIL, object.getEmail());
    }
}
