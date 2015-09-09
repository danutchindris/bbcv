package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.vo.User;
import ro.leje.rest.UserServiceConsumer;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 15, 2015, Catiasu
 */
@Controller
@RequestMapping(value = MappingConstants.ADMIN)
public class AdminController {

    private static final String USERS = "users";

    @Resource
    private LanguageDelegate languageDelegate;

    @Resource
    private UserServiceConsumer userServiceConsumer;

    @RequestMapping(value = MappingConstants.USER_LIST, method = RequestMethod.GET)
    public String displayUserList(Model model) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_LIST;
    }

    @RequestMapping(MappingConstants.USER_LIST_JSON)
    public @ResponseBody List<User> findUsers() {
        return userServiceConsumer.findAll();
    }

    @RequestMapping(value = MappingConstants.USER, method = RequestMethod.POST)
    @ResponseBody
    public Long addUser(@RequestBody User user) {
        return userServiceConsumer.create(user);
    }

    @RequestMapping(value = MappingConstants.ROLE_LIST, method = RequestMethod.GET)
    public String displayRoleList(Model model) {
        // model.addAttribute(ROLES, userServiceConsumer.findAll());
        return ViewConstants.ADMIN + "/" + ViewConstants.ROLE_LIST;
    }
}
