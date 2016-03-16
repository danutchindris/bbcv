package ro.leje.service;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.leje.AbstractTest;
import ro.leje.model.vo.Permission;
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

    private final static long TEST_ROLE_NOT_EXISTING_ID = Long.MAX_VALUE;

    private final static long TEST_PERMISSION_ID = 1L;
    private final static String TEST_PERMISSION_NAME = "permission_admin_user_post";

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
    public void findAllReturnsNotEmptyList() {
        List<User> list = service.findAll();
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain two items", list.size() == 2);
    }

    @Test
    public void findByUserNameReturnsNotNullWhenExistingUserNameIsProvided() {
        User object = service.findByUserName(TEST_USER_USERNAME);
        checkUserObjectProperties(object);
    }

    @Test
    public void findByUserNameReturnsNullWhenNotExistingUserNameIsProvided() {
        User object = service.findByUserName(TEST_USER_NOT_EXISTING_USERNAME);
        Assert.assertNull("The returned object for a not existing user name should be null", object);
    }

    @Test(expected = NullPointerException.class)
    public void findByUserNameThrowsExceptionWhenUserNameIsNull() {
        service.findByUserName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUserNameThrowsExceptionWhenUserNameIsEmpty() {
        service.findByUserName("");
    }

    @Test
    public void findByIdReturnsNotNullWhenExistingUserIdIsProvided() {
        User object = service.findById(TEST_USER_ID);
        checkUserObjectProperties(object);
    }

    @Test
    public void findByIdReturnsNullWhenNotExistingUserIdIsProvided() {
        User object = service.findById(Long.MAX_VALUE);
        Assert.assertNull("The returned object for a not existing user id should be null", object);
    }

    @Test
    public void createUserReturnsEntityIdWhenMandatoryFieldsAreProvided() {
        User object = new User();
        object.setUserName(TEST_USER_NEW_USERNAME);
        object.setPassword(TEST_USER_NEW_PASSWORD);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        object.setEmail(TEST_USER_NEW_EMAIL);
        Assert.assertNotNull("The user entity should have been created", service.create(object));
    }

    @Test(expected = NullPointerException.class)
    public void createUserThrowsExceptionWhenMandatoryUserNameIsNotProvided() {
        User object = new User();
        object.setPassword(TEST_USER_NEW_PASSWORD);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        object.setEmail(TEST_USER_NEW_EMAIL);
        service.create(object);
    }

    @Test(expected = NullPointerException.class)
    public void createUserThrowsExceptionWhenMandatoryPasswordIsNotProvided() {
        User object = new User();
        object.setUserName(TEST_USER_NEW_USERNAME);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        object.setEmail(TEST_USER_NEW_EMAIL);
        service.create(object);
    }

    @Test(expected = NullPointerException.class)
    public void createUserThrowsExceptionWhenMandatoryEmailIsNotProvided() {
        User object = new User();
        object.setUserName(TEST_USER_NEW_USERNAME);
        object.setPassword(TEST_USER_NEW_PASSWORD);
        object.setFirstName(TEST_USER_NEW_FIRSTNAME);
        object.setLastName(TEST_USER_NEW_LASTNAME);
        service.create(object);
    }

    @Test(expected = NullPointerException.class)
    public void createThrowsExceptionWhenTheUserObjectIsNotProvided() {
        service.create(null);
    }

    @Test
    public void findRolesReturnsExpectedListWhenExistingUserIdIsProvided() {
        List<Role> list = service.findRoles(TEST_USER_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain one item", list.size() == 1);
        Assert.assertEquals("The id is incorrect", TEST_ROLE_ID, list.get(0).getId());
        Assert.assertEquals("The name is incorrect", TEST_ROLE_NAME, list.get(0).getName());
    }

    @Test
    public void findRolesReturnsNotNullListWhenNotExistingUserIdIsProvided() {
        List<Role> list = service.findRoles(TEST_USER_NOT_EXISTING_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain zero items", list.size() == 0);
    }

    @Test
    public void findRolesReturnsEmptyListWhenExistingUserIdWithoutRolesIsProvided() {
        List<Role> list = service.findRoles(TEST_USER_WITHOUT_ROLES_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain zero items", list.size() == 0);
    }

    @Test
    public void findRoleReturnsExpectedObjectWhenExistingAssignationIsProvided() {
        Role object = service.findRole(TEST_USER_ID, TEST_ROLE_ID);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The id is incorrect", TEST_ROLE_ID, object.getId());
        Assert.assertEquals("The name is incorrect", TEST_ROLE_NAME, object.getName());
    }

    @Test(expected = ContextedRuntimeException.class)
    public void findRoleThrowsExceptionWhenNotExistingAssignationIsProvided() {
        service.findRole(TEST_USER_WITHOUT_ROLES_ID, TEST_ROLE_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findRoleThrowsExceptionWhenNotExistingUserIdIsProvided() {
        service.findRole(TEST_USER_NOT_EXISTING_ID, TEST_ROLE_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findRoleThrowsExceptionWhenNotExistingRoleIdIsProvided() {
        service.findRole(TEST_USER_ID, TEST_ROLE_NOT_EXISTING_ID);
    }

    @Test
    public void isRoleAssignedReturnsTrueWhenExistingAssignationIsProvided() {
        Assert.assertTrue("The method should have detected the existing assignation",
                service.isRoleAssigned(TEST_USER_ID, TEST_ROLE_ID));
    }

    @Test
    public void isRoleAssignedReturnsFalseWhenNotExistingAssignationIsProvided() {
        Assert.assertTrue("The method should have detected that there is no assignation",
                !service.isRoleAssigned(TEST_USER_WITHOUT_ROLES_ID, TEST_ROLE_ID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRoleAssignedThrowsExceptionWhenNotExistingUserIdIsProvided() {
        service.isRoleAssigned(TEST_USER_NOT_EXISTING_ID, TEST_ROLE_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRoleAssignedThrowsExceptionWhenNotExistingRoleIdIsProvided() {
        service.isRoleAssigned(TEST_USER_ID, TEST_ROLE_NOT_EXISTING_ID);
    }

    /**
     * We assume the transaction is rolled back thanks to the @Transactional annotation
     * at class level
     */
    @Test
    public void assignRoleThrowsNoExceptionWhenExistingUserIdAndExistingRoleIdAreProvided() {
        service.assignRole(TEST_USER_WITHOUT_ROLES_ID, TEST_ROLE_ID);
    }

    @Test(expected = ContextedRuntimeException.class)
    public void assignRoleThrowsExceptionWhenAlreadyAssignedRoleIdIsProvidedToExistingUser() {
        service.assignRole(TEST_USER_ID, TEST_ROLE_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignRoleThrowsExceptionWhenNotExistingUserIdIsProvided() {
        service.assignRole(TEST_USER_NOT_EXISTING_ID, TEST_ROLE_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignRoleThrowsExceptionWhenNotExistingRoleIdIsProvided() {
        service.assignRole(TEST_USER_ID, TEST_ROLE_NOT_EXISTING_ID);
    }

    @Test
    public void findPermissionsReturnsExpectedListWhenExistingUserIdIsProvided() {
        List<Permission> list = service.findPermissions(TEST_USER_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain three items", list.size() == 3);
        Assert.assertEquals("The id is incorrect", TEST_PERMISSION_ID, list.get(0).getId());
        Assert.assertEquals("The name is incorrect", TEST_PERMISSION_NAME, list.get(0).getName());
    }

    @Test
    public void findPermissionsReturnsNotNullListWhenNotExistingUserIdIsProvided() {
        List<Permission> list = service.findPermissions(TEST_USER_NOT_EXISTING_ID);
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain zero items", list.size() == 0);
    }

    @Test
    public void findPermissionsReturnsEmptyListWhenExistingUserWithoutPermissionsIsProvided() {
        List<Permission> list = service.findPermissions(TEST_USER_WITHOUT_ROLES_ID);
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
