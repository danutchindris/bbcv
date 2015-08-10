package ro.leje.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.leje.model.vo.User;
import ro.leje.service.UserService;

import java.util.List;

/**
 * @author Danut Chindris
 * @since July 11, 2015
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    /*@Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{firstName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ro.leje.model.vo.User> getUsersByFirstName(@PathVariable String firstName) {
        List<User> userEntities = userRepository.findByFirstName(firstName);
        List<ro.leje.model.vo.User> users = new ArrayList<>();
        for (User userEntity : userEntities) {
            users.add(new ro.leje.model.vo.User(userEntity.getFirstName(), userEntity.getLastName()));
        }
        return users;
    }*/

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ro.leje.model.vo.User> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return users;
    }
}
