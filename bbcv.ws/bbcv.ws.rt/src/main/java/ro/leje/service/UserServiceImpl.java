package ro.leje.service;

import org.apache.commons.lang3.Validate;
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
    public List<User> findAll() {
        List<ro.leje.model.entity.User> userEntities = userDAO.findAllUsers();
        List<User> users = new ArrayList<>();
        for (ro.leje.model.entity.User userEntity : userEntities) {
            User userVO = new ro.leje.model.vo.User();
            userVO.setId(userEntity.getId());
            userVO.setUserName(userEntity.getUserName());
            userVO.setFirstName(userEntity.getFirstName());
            userVO.setLastName(userEntity.getLastName());
            userVO.setEmail(userEntity.getEmail());
            users.add(userVO);
        }
        return users;
    }

    @Override
    @Transactional
    public User findByUserName(String userName) {
        Validate.notEmpty(userName, "Null or empty user name not allowed");
        return userDAO.findByUserName(userName);
    }

    @Override
    @Transactional
    public User findById(long id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional
    public Long create(String userName, String password, String firstName, String lastName, String email) {
        Validate.notEmpty(userName, "Null or empty user name not allowed");
        Validate.notEmpty(password, "Null or empty password not allowed");
        Validate.notEmpty(userName, "Null or empty email not allowed");
        ro.leje.model.entity.User user = new ro.leje.model.entity.User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return userDAO.create(user);
    }
}
