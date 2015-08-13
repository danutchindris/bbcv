package ro.leje.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.leje.model.vo.User;
import ro.leje.service.UserService;
import ro.leje.util.RestMappings;

import java.util.List;

/**
 * @author Danut Chindris
 * @since July 11, 2015
 */
@RestController
@RequestMapping(RestMappings.USER)
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = RestMappings.USER_FIND_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ro.leje.model.vo.User> getAllUsers() {
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping(value = RestMappings.USER_FIND_BY_USERNAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findByUserName(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }
}
