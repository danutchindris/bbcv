package ro.leje.rest.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.leje.AbstractControllerTest;
import ro.leje.util.RestMappings;

import javax.transaction.Transactional;

/**
 * @author Danut Chindris
 * @since August 12, 2015
 */
@Transactional
public class UserRestControllerTest extends AbstractControllerTest {

    private static final String TEST_USERNAME = "test.user";

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testFindByUserName() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(RestMappings.USER + RestMappings.USER_FIND_BY_USERNAME, TEST_USERNAME)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("The expected HTTP status is 200", 200, status);
        Assert.assertTrue("The HTTP response body is expected to be non empty", content.trim().length() > 0);
    }
}
