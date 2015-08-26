package ro.leje.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ro.leje.AbstractWebAppTest;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.util.ConfigConstants;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Danut Chindris
 * @since August 12, 2015
 */
public class HomeControllerTest extends AbstractWebAppTest {

    @Mock
    private LanguageDelegate languageDelegate;

    @InjectMocks
    HomeController homeController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(ConfigConstants.TEMPLATES_PREFIX);
        viewResolver.setSuffix(ConfigConstants.TEMPLATES_SUFFIX);
        // for unit tests use the standaloneSetup() method
        // for integration tests use the webAppContextSetup() method
        mockMvc = MockMvcBuilders.standaloneSetup(homeController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testHomeController() throws Exception {
        mockMvc.perform(get(MappingConstants.ROOT))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewConstants.HOME));
        mockMvc.perform(get(MappingConstants.HOME))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewConstants.HOME));
    }
}
