package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.leje.dao.UserDAO;
import ro.leje.model.entity.UserEntity;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<User> findAll() {
        return userDAO.findAll();
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
        Validate.notEmpty(user.getEmail(), "Null or empty email not allowed");
        Validate.notNull(user.getEnabled(), "Null enabled flag not allowed");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setEnabled(user.getEnabled());
        return userDAO.create(userEntity);
    }

    @Override
    @Transactional
    public List<Role> findRoles(long id) {
        return userDAO.findRoles(id);
    }
}
