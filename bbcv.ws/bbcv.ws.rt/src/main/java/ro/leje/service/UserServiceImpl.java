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
    public Long create(User user) {
        Validate.notNull(user, "Null user object not allowed");
        Validate.notEmpty(user.getUserName(), "Null or empty user name not allowed");
        Validate.notEmpty(user.getPassword(), "Null or empty password not allowed");
        Validate.notEmpty(user.getUserName(), "Null or empty email not allowed");
        ro.leje.model.entity.User userEntity = new ro.leje.model.entity.User();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        return userDAO.create(userEntity);
    }
}
