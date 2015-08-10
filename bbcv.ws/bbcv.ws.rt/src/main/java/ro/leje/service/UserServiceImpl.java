package ro.leje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.leje.dao.UserDAO;
import ro.leje.model.vo.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List<User> findAllUsers() {
        List<ro.leje.model.entity.User> userEntities = userDAO.findAllUsers();
        List<User> users = new ArrayList<>();
        for (ro.leje.model.entity.User userEntity : userEntities) {
            users.add(new ro.leje.model.vo.User(userEntity.getFirstName(), userEntity.getLastName()));
        }
        return users;
    }
}
