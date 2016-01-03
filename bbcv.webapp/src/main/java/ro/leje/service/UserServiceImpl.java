package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import ro.leje.dao.UserDAO;
import ro.leje.model.entity.RoleEntity;
import ro.leje.model.entity.UserEntity;
import ro.leje.model.vo.Permission;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    private <T> void validateId(long id, Class<T> clazz) {
        T entity = userDAO.findEntity(id, clazz);
        if (entity == null) {
            throw new IllegalArgumentException("No " + clazz.getName() + " entity found for id: " + id);
        }
    }

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
    public List<Role> findRoles(long userId) {
        return userDAO.findRoles(userId);
    }

    @Override
    @Transactional
    public Role findRole(long userId, long roleId) {
        validateId(userId, UserEntity.class);
        validateId(roleId, RoleEntity.class);
        Role role = userDAO.findRole(userId, roleId);
        if (role == null) {
            throw new ContextedRuntimeException("No user role assignation found")
                    .addContextValue("userId", userId)
                    .addContextValue("roleId", roleId);
        }
        return role;
    }

    @Override
    @Transactional
    public boolean isRoleAssigned(long userId, long roleId) {
        validateId(userId, UserEntity.class);
        validateId(roleId, RoleEntity.class);
        return (userDAO.findRole(userId, roleId) != null);
    }

    @Override
    @Transactional
    public void addRole(long userId, long roleId) {
        validateId(userId, UserEntity.class);
        validateId(roleId, RoleEntity.class);
        // check if the role is already assigned or not
        if (isRoleAssigned(userId, roleId)) {
            throw new ContextedRuntimeException("Role is already assigned")
                    .addContextValue("userId", userId)
                    .addContextValue("roleId", roleId);
        }
        userDAO.addRole(userId, roleId);
    }

    @Override
    @Transactional
    public List<Permission> findPermissions(long userId) {
        return userDAO.findPermissions(userId);
    }
}
