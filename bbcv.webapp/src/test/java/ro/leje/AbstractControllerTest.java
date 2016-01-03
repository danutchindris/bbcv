package ro.leje;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @author Danut Chindris
 * @since August 12, 2015
 */
@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractTest {

    protected MockMvc mvc;

    @Resource
    protected WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
