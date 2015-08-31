package ro.leje.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.leje.AbstractTest;
import ro.leje.model.vo.Role;

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
}
