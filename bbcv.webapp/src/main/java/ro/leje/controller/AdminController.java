package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.model.vo.User;
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

    @RequestMapping(value = MappingConstants.USER, method = RequestMethod.POST)
    @ResponseBody
    public void addUser(@RequestBody User user) {
        userServiceConsumer.getUsersByFirstName(user.getFirstName());
    }
}
