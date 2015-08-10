package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

/**
 * @author Danut Chindris
 * @since August 10, 2015
 */
@Controller
public class LoginController {

    @RequestMapping(value = MappingConstants.LOGIN)
    public String login() {
        return ViewConstants.LOGIN;
    }
}
