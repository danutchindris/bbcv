package ro.leje.rest;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.junit.Assert;
import org.junit.Test;
import ro.leje.AbstractWebAppTest;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 27, 2015
 */
public class UserServiceConsumerTest extends AbstractWebAppTest {

    private final static long TEST_USER_ID = 1L;
    private final static String TEST_USER_USER_NAME = "test.user";
    private final static String TEST_USER_FIRST_NAME = "Test";
    private final static String TEST_USER_LAST_NAME = "User";
    private final static String TEST_USER_EMAIL = "test.user@testuser.app";

    private final static long TEST_USER_WITHOUT_ROLES_ID = 2L;

    private final static long TEST_USER_NOT_EXISTING_ID = Long.MAX_VALUE;
    private final static String NOT_EXISTING_TEST_USER_USER_NAME = "not.existing.test.user";

    private final static long TEST_ROLE_ID = 1L;
    private final static String TEST_ROLE_NAME = "admin";

    private final static long TEST_ROLE_NOT_EXISTING_ID = Long.MAX_VALUE;

    @Resource
    private UserServiceConsumer service;

    @Test
    public void whenCalledFindAllReturnsANotEmptyList() {
        List<User> list = service.findAll();
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain two items", list.size() == 2);
    }

    @Test
    public void whenCalledFindAllReturnsAListHavingTheExpectedFirstItem() {
        List<User> list = service.findAll();
        User object = list.get(0);
        checkUserObjectProperties(object);
    }

    @Test
    public void whenCalledWithExistingUserNameFindByUserNameReturnsNotNull() {
        User object = service.findByUserName(TEST_USER_USER_NAME);
        Assert.assertNotNull("The returned object shouldn't be null", object);
    }

    @Test
    public void whenCalledWithNotExistingUserNameFindByUserNameReturnsNotNull() {
        User object = service.findByUserName(NOT_EXISTING_TEST_USER_USER_NAME);
        Assert.assertNotNull("The returned object shouldn't be null", object);
    }

    @Test
    public void whenCalledWithExistingUserNameFindByUserNameReturnsTheExpectedObject() {
        User object = service.findByUserName(TEST_USER_USER_NAME);
        checkUserObjectProperties(object);
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

    @Test
    public void whenExistingUserIdAndExistingRoleIdAreProvidedAddRoleReturnsBoolean() {
        Assert.assertTrue("The method call should have returned 'true'",
                service.addRole(TEST_USER_WITHOUT_ROLES_ID, TEST_ROLE_ID));
    }

    @Test(expected = ContextedRuntimeException.class)
    public void whenAnAlreadyAssignedRoleIdIsProvidedToAnExistingUserAddRoleThrowsException() {
        service.addRole(TEST_USER_ID, TEST_ROLE_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotExistingUserIdIsProvidedAddRoleThrowsException() {
        service.addRole(TEST_USER_NOT_EXISTING_ID, TEST_ROLE_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotExistingRoleIdIsProvidedAddRoleThrowsException() {
        service.addRole(TEST_USER_ID, TEST_ROLE_NOT_EXISTING_ID);
    }

    private void checkUserObjectProperties(User object) {
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The id is incorrect", TEST_USER_ID, object.getId());
        Assert.assertEquals("The user name is incorrect", TEST_USER_USER_NAME, object.getUserName());
        Assert.assertEquals("The first name is incorrect", TEST_USER_FIRST_NAME, object.getFirstName());
        Assert.assertEquals("The last name is incorrect", TEST_USER_LAST_NAME, object.getLastName());
        Assert.assertEquals("The email is incorrect", TEST_USER_EMAIL, object.getEmail());
    }
}
