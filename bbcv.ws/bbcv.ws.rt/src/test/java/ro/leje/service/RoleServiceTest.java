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
 * @since August 31, 2015
 */
@Transactional
public class RoleServiceTest extends AbstractTest {

    private final static long TEST_ROLE_INVALID_ID = 0L;

    private final static long TEST_ROLE_NEW_ID = Long.MAX_VALUE;
    private final static String TEST_ROLE_NEW_NAME = "new.role";

    private final static long TEST_ROLE_EXISTING_ID = 1L;
    private final static String TEST_ROLE_EXISTING_NAME = "admin";

    @Resource
    private RoleService service;

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
        List<Role> list = service.findAll();
        Assert.assertNotNull("The list shouldn't be null", list);
        Assert.assertTrue("The list should contain one item", list.size() == 2);
    }

    @Test
    public void whenMandatoryFieldsAreProvidedCreateReturnsEntityId() {
        Role object = new Role();
        object.setName(TEST_ROLE_NEW_NAME);
        Long id = service.create(object);
        Assert.assertNotNull("The entity id shouldn't be null", id);
        Assert.assertTrue("The entity id should be a positive integer", id > 0);
    }

    @Test(expected = NullPointerException.class)
    public void whenMandatoryNameIsNotProvidedCreateThrowsException() {
        Role object = new Role();
        service.create(object);
    }

    @Test(expected = NullPointerException.class)
    public void whenTheRoleObjectIsNotProvidedCreateThrowsException() {
        service.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddingAnExistingRoleNameCreateThrowsExcetion() {
        Role object = new Role();
        object.setName(TEST_ROLE_EXISTING_NAME);
        service.create(object);
    }

    @Test
    public void whenCalledWithAnExistingNameFindReturnsValueObject() {
        Role object = service.find(TEST_ROLE_EXISTING_NAME);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The returned name should be " + TEST_ROLE_EXISTING_NAME,
                TEST_ROLE_EXISTING_NAME, object.getName());
    }

    @Test
    public void whenCalledWithANotExistingNameFindReturnsEmptyValueObject() {
        Role object = service.find(TEST_ROLE_NEW_NAME);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The returned id should be zero", 0L, object.getId());
        Assert.assertNull("The returned name should be null", object.getName());
    }

    @Test
    public void whenCalledWithAnExistingIdFindReturnsValueObject() {
        Role object = service.find(TEST_ROLE_EXISTING_ID);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The returned id should be " + TEST_ROLE_EXISTING_ID,
                TEST_ROLE_EXISTING_ID, object.getId());
        Assert.assertEquals("The returned name should be " + TEST_ROLE_EXISTING_NAME,
                TEST_ROLE_EXISTING_NAME, object.getName());
    }

    @Test
    public void whenCalledWithANotExistingIdFindReturnsEmptyValueObject() {
        Role object = service.find(TEST_ROLE_NEW_ID);
        Assert.assertNotNull("The returned object shouldn't be null", object);
        Assert.assertEquals("The returned id should be zero", 0L, object.getId());
        Assert.assertNull("The returned name should be null", object.getName());
    }

    @Test(expected = NullPointerException.class)
    public void whenCalledWithNullParameterUpdateThrowsException() {
        service.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledWithInvalidIdUpdateThrowsException() {
        Role object = new Role();
        object.setId(TEST_ROLE_INVALID_ID);
        object.setName(TEST_ROLE_NEW_NAME);
        service.update(object);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledWithNotExistingIdUpdateThrowsException() {
        Role object = new Role();
        object.setId(TEST_ROLE_NEW_ID);
        object.setName(TEST_ROLE_NEW_NAME);
        service.update(object);
    }

    @Test(expected = NullPointerException.class)
    public void whenCalledWithNullNameUpdateThrowsException() {
        Role object = service.find(TEST_ROLE_EXISTING_ID);
        object.setName(null);
        service.update(object);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledWithEmptyNameUpdateThrowsException() {
        Role object = service.find(TEST_ROLE_EXISTING_ID);
        object.setName("");
        service.update(object);
    }

    @Test
    public void whenCalledWithValidValuesUpdatePersistsTheNewValues() {
        Role object = service.find(TEST_ROLE_EXISTING_ID);
        object.setName(TEST_ROLE_NEW_NAME);
        service.update(object);
        object = service.find(TEST_ROLE_EXISTING_ID);
        Assert.assertEquals("The returned name should be " + TEST_ROLE_NEW_NAME,
                TEST_ROLE_NEW_NAME, object.getName());
    }

    @Test
    public void whenCalledWithExistingIdDeletePersistsTheChanges() {
        Role object = new Role();
        object.setName(TEST_ROLE_NEW_NAME);
        Long id = service.create(object);
        service.delete(id);
        object = service.find(id);
        Assert.assertEquals("The returned id should be zero", 0L, object.getId());
        Assert.assertNull("The returned name should be null", object.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledWithNotExistingIdDeleteThrowsException() {
        service.delete(TEST_ROLE_NEW_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledWithAnIdReferencedInAnotherTableDeleteThrowsException() {
        service.delete(TEST_ROLE_EXISTING_ID);
    }

    @Test
    public void whenCalledWithExistingIdFindUsersReturnsExpectedList() {
        List<User> list = service.findUsers(TEST_ROLE_EXISTING_ID);
        Assert.assertNotNull("The returned list shouldn't be null", list);
        Assert.assertTrue("The returned list should contain one item", list.size() == 1);
    }

    @Test
    public void whenCalledWithNotExistingIdFindUsersReturnsNotNullList() {
        List<User> list = service.findUsers(TEST_ROLE_NEW_ID);
        Assert.assertNotNull("The returned list shouldn't be null", list);
        Assert.assertTrue("The returned list should be empty", list.isEmpty());
    }
}
