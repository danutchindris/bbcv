package ro.leje.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.leje.AbstractTest;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Marking the test class as {@link javax.transaction.Transactional},
 * rolls back automatically any change performed on the database.
 * @author Danut Chindris
 * @since August 12, 2015
 */
@Transactional
public class UserServiceTest extends AbstractTest {

    private final static long TEST_USER_ID = 1L;
    private final static String TEST_USER_USERNAME = "test.user";
    private final static String TEST_USER_FIRSTNAME = "Test";
    private final static String TEST_USER_LASTNAME = "User";
    private final static String TEST_USER_EMAIL = "test.user@testuser.app";

    private final static long TEST_USER_WITHOUT_ROLES_ID = 2L;

    private final static long TEST_USER_NOT_EXISTING_ID = Long.MAX_VALUE;
    private final static String TEST_USER_NOT_EXISTING_USERNAME = "not.existing.user";

    private final static String TEST_USER_NEW_USERNAME = "test.new.user";
    private final static String TEST_USER_NEW_PASSWORD = "$2a$11$WYWaOD55tFDhUQquHY.RZ.yEW5YS1lGkt7CiqRYAs79Fa6j.hnPaO";
    private final static String TEST_USER_NEW_FIRSTNAME = "Test.New";
    private final static String TEST_USER_NEW_LASTNAME = "User.New";
    private final static String TEST_USER_NEW_EMAIL = "test.new.user@testuser.app";

    private final static long TEST_ROLE_ID = 1L;
    private final static String TEST_ROLE_NAME = "admin";

    @Resource
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
    public void whenCalledFindAllReturnsANotEmptyList() {
        List<User> list = service.findAll();
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain two items", list.size() == 2);
    }

    @Test
    public void whenCorrectUserNameIsProvidedFindByUserNameReturnsNotNull() {
        User object = service.findByUserName(TEST_USER_USERNAME);
        checkUserObjectProperties(object);
    }

    @Test
    public void whenNotExistingUserNameIsProvidedFindByUserNameReturnsNull() {
        User object = service.findByUserName(TEST_USER_NOT_EXISTING_USERNAME);
        Assert.assertNull("The returned object for a not existing user name should be null", object);
    }

    @Test(expected = NullPointerException.class)
    public void whenUserNameIsNullFindByUserNameThrowsException() {
        service.findByUserName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUserNameIsEmptyFindByUserNameThrowsException() {
        service.findByUserName("");
    }

    @Test
    public void whenCorrectUserIdIsProvidedFindByIdReturnsNotNull() {
        User object = service.findById(TEST_USER_ID);
        checkUserObjectProperties(object);
    }

    @Test
    public void whenIncorrectUserIdIsProvidedFindByIdReturnsNull() {
        User object = service.findById(Long.MAX_VALUE);
        Assert.assertNull("The returned object for an incorrect id should be null", object);
    }

    @Test
    public void whenMandatoryFieldsAreProvidedCreateUserReturnsEntityId() {
        User object = new User();
        object.setUserName(TEST_USER_NEW_USERNAME);
        object.setPassword(TEST_USER_NEW_PASSWORD);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        object.setEmail(TEST_USER_NEW_EMAIL);
        Assert.assertNotNull("The user entity should have been created", service.create(object));
    }

    @Test(expected = NullPointerException.class)
    public void whenMandatoryUserNameIsNotProvidedCreateUserThrowsException() {
        User object = new User();
        object.setPassword(TEST_USER_NEW_PASSWORD);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        object.setEmail(TEST_USER_NEW_EMAIL);
        service.create(object);
    }

    @Test(expected = NullPointerException.class)
    public void whenMandatoryPasswordIsNotProvidedCreateUserThrowsException() {
        User object = new User();
        object.setUserName(TEST_USER_NEW_USERNAME);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        object.setEmail(TEST_USER_NEW_EMAIL);
        service.create(object);
    }

    @Test(expected = NullPointerException.class)
    public void whenMandatoryEmailIsNotProvidedCreateUserThrowsException() {
        User object = new User();
        object.setUserName(TEST_USER_NEW_USERNAME);
        object.setPassword(TEST_USER_NEW_PASSWORD);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        service.create(object);
    }

    @Test(expected = NullPointerException.class)
    public void whenTheUserObjectIsNotProvidedCreateThrowsException() {
        service.create(null);
    }

    @Test
    public void whenExistingUserIdIsProvidedFindRolesReturnsExpectedList() {
        List<Role> list = service.findRoles(TEST_USER_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain one item", list.size() == 1);
        Assert.assertEquals("The id is incorrect", TEST_ROLE_ID, list.get(0).getId());
        Assert.assertEquals("The name is incorrect", TEST_ROLE_NAME, list.get(0).getName());
    }

    @Test
    public void whenNotExistingUserIdIsProvidedFindRolesReturnsNotNullList() {
        List<Role> list = service.findRoles(TEST_USER_NOT_EXISTING_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain zero items", list.size() == 0);
    }

    @Test
    public void whenExistingUserWithoutRolesIsProvidedFindRolesReturnsEmptyList() {
        List<Role> list = service.findRoles(TEST_USER_WITHOUT_ROLES_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain zero items", list.size() == 0);
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
