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
public class UsersRestControllerTest extends AbstractControllerTest {

    private static final String TEST_USER_NAME = "test.user";

    private static final String TEST_NEW_USER_NAME = "new.test.user";
    private static final String TEST_NEW_PASSWORD = "new.test.password";
    private static final String TEST_NEW_EMAIL = "new.test.email";

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testFindByUserName() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(RestMappings.API + RestMappings.V1 + RestMappings.USERS + RestMappings.USER_NAME, TEST_USER_NAME)
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
        object.setUserName(TEST_NEW_USER_NAME);
        object.setPassword(TEST_NEW_PASSWORD);
        object.setEmail(TEST_NEW_EMAIL);
        Gson gson = new Gson();
        String json = gson.toJson(object);
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(RestMappings.API + RestMappings.V1 + RestMappings.USERS)
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
