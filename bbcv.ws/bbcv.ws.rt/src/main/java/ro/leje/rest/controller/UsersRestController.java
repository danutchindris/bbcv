package ro.leje.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.leje.model.vo.Permission;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;
import ro.leje.service.UserService;
import ro.leje.util.RestMappings;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since July 11, 2015
 */
@RestController
@RequestMapping(RestMappings.API + RestMappings.V1 + RestMappings.USERS)
public class UsersRestController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ro.leje.model.vo.User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = RestMappings.USER_NAME,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User findByUserName(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Long create(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(value = RestMappings.USER_ID + RestMappings.ROLES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findRoles(@PathVariable long userId) {
        return userService.findRoles(userId);
    }

    @RequestMapping(value = RestMappings.USER_ID + RestMappings.ROLES + RestMappings.ROLE_ID,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addRole(@PathVariable long userId, @PathVariable long roleId) {
        userService.addRole(userId, roleId);
    }

    @RequestMapping(value = RestMappings.USER_ID + RestMappings.PERMISSIONS,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Permission> findPermissions(@PathVariable long userId) {
        return userService.findPermissions(userId);
    }
}
