package ro.leje.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ro.leje.ApplicationTest;
import ro.leje.model.vo.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Marking the test class as {@link javax.transaction.Transactional}, rolls back automatically any change performed on the database.
 * @author Danut Chindris
 * @since August 12, 2015
 */
@Transactional
public class UserServiceTest extends ApplicationTest {

    @Autowired
    private UserService service;

    @Before
    public void setUp() {
        // set up before each test method in the class
    }

    @After
    public void tearDown() {
        // clean up after each test method in the class
    }

    @Test
    public void testFindAll() {
        List<User> list = service.findAll();
        Assert.assertNotNull("The list shouldn't be null", list);
    }

    @Test
    public void whenUserNameIsEmptyFindByUserNameReturnsNull() {
        User object = service.findByUserName("");
        Assert.assertNull("The returned object should be null", object);
    }

    @Test
    public void whenCorrectUserNameIsProvidedFindByUserNameReturnsNotNull() {
        User object = service.findByUserName("test.user");
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The id is incorrect", 1L, object.getId());
        Assert.assertEquals("The user name is incorrect", "test.user", object.getUserName());
        Assert.assertEquals("The first name is incorrect", "Test", object.getFirstName());
        Assert.assertEquals("The last name is incorrect", "User", object.getLastName());
        Assert.assertEquals("The email is incorrect", "test.user@testuser.app", object.getEmail());
    }
}
