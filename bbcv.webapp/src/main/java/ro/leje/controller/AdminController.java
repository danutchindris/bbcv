package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

/**
 * @author Danut Chindris
 * @since August 15, 2015, Catiasu
 */
@Controller
public class AdminController {

    @RequestMapping(MappingConstants.ADMIN)
    public String admin() {
        return ViewConstants.ADMIN;
    }
}
