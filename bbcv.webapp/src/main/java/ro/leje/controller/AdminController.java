package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.leje.rest.UserServiceConsumer;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;

/**
 * @author Danut Chindris
 * @since August 15, 2015, Catiasu
 */
@Controller
@RequestMapping(value = MappingConstants.ADMIN)
public class AdminController {

    private static final String USERS = "users";

    @Resource
    private UserServiceConsumer userServiceConsumer;

    @RequestMapping(value = MappingConstants.USER_LIST, method = RequestMethod.GET)
    public String adminList(Model model) {
        model.addAttribute(USERS, userServiceConsumer.getAllUsers());
        return ViewConstants.USER_LIST;
    }
}
