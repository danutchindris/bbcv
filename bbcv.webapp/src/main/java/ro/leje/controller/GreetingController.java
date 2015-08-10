package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.model.vo.User;
import ro.leje.rest.UserServiceConsumer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since July 5, 2015
 */
@Controller
public class GreetingController {

    @Resource
    UserServiceConsumer userService;

    @RequestMapping("/greeting")
    public String greeting(Model model) {
        List<User> users = userService.getUsersByFirstName("Danut");
        if (!users.isEmpty()) {
            model.addAttribute("lastName", users.get(0).getLastName());
        }
        return "greeting";
    }
}
