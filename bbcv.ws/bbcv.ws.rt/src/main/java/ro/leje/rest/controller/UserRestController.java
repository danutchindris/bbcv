package ro.leje.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.leje.model.vo.Role;
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

    @RequestMapping(value = RestMappings.FIND_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ro.leje.model.vo.User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping(value = RestMappings.USER_FIND_BY_USER_NAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findByUserName(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }

    @RequestMapping(value = RestMappings.USER_FIND_BY_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Long create(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(value = RestMappings.USER_FIND_ROLES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findRoles(@PathVariable long id) {
        return userService.findRoles(id);
    }
}
