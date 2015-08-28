package ro.leje.rest.controller;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.leje.AbstractControllerTest;
import ro.leje.model.vo.User;
import ro.leje.util.RestMappings;

import javax.transaction.Transactional;

/**
 * @author Danut Chindris
 * @since August 12, 2015
 */
@Transactional
public class UserRestControllerTest extends AbstractControllerTest {

    private static final String TEST_USER_USERNAME = "test.user";
    private static final long TEST_USER_ID = 1L;

    private static final String TEST_NEW_USER_USERNAME = "new.test.user";
    private static final String TEST_NEW_USER_PASSWORD = "new.test.password";
    private static final String TEST_NEW_USER_EMAIL = "new.test.email";

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testFindByUserName() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(RestMappings.USER + RestMappings.USER_FIND_BY_USER_NAME, TEST_USER_USERNAME)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("The expected HTTP status is 200", 200, status);
        Assert.assertTrue("The HTTP response body is expected to be non empty", content.trim().length() > 0);
    }

    @Test
    public void testFindById() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(RestMappings.USER + RestMappings.USER_FIND_BY_ID, TEST_USER_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("The expected HTTP status is 200", 200, status);
        Assert.assertTrue("The HTTP response body is expected to be non empty", content.trim().length() > 0);
    }

    @Test
    public void testCreate() throws Exception {
        User object = new User();
        object.setUserName(TEST_NEW_USER_USERNAME);
        object.setPassword(TEST_NEW_USER_PASSWORD);
        object.setEmail(TEST_NEW_USER_EMAIL);
        Gson gson = new Gson();
        String json = gson.toJson(object);
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(RestMappings.USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("The expected HTTP status is 200", 200, status);
        Assert.assertTrue("The HTTP response body is expected to be non empty", content.trim().length() > 0);
    }
}
