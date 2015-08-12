package ro.leje;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * We are using a special profile that accesses a database set up for testing purposes.
 * This profile is activated via the {@link org.springframework.test.context.ActiveProfiles} annotation
 *
 * @author Danut Chindris
 * @since August 12, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(profiles = "localtest")
public abstract class ApplicationTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
