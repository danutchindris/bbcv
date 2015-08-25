package ro.leje.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.leje.AbstractWebAppTest;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

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
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
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
