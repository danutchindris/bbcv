package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import ro.leje.dao.RoleDAO;
import ro.leje.model.entity.RoleEntity;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public List<Role> findAll() {
        List<RoleEntity> entities = roleDAO.findAll();
        List<Role> roles = new ArrayList<>();
        for (RoleEntity entity : entities) {
            Role role = new Role();
            role.setId(entity.getId());
            role.setName(entity.getName());
            roles.add(role);
        }
        return roles;
    }

    @Override
    @Transactional
    public Long create(Role role) {
        Validate.notNull(role, "Null role object not allowed");
        Validate.notEmpty(role.getName(), "Null or empty name not allowed");
        // we can't add a new role in the database that has an existing name
        Role checkExistingName = find(role.getName());
        if (checkExistingName.getName() != null) {
            throw new IllegalArgumentException("Role already exists");
        }
        RoleEntity entity = new RoleEntity();
        entity.setName(role.getName());
        return roleDAO.create(entity);
    }

    @Override
    @Transactional
    public Role find(String name) {
        Role object = new Role();
        List<RoleEntity> list = roleDAO.find(name);
        if (!list.isEmpty()) {
            // we know there should be only one object with this name in the database
            object.setId(list.get(0).getId());
            object.setName(list.get(0).getName());
        }
        return object;
    }

    @Override
    @Transactional
    public Role find(long id) {
        Role object = new Role();
        RoleEntity entity = roleDAO.find(id);
        if (entity != null) {
            object.setId(entity.getId());
            object.setName(entity.getName());
        }
        return object;
    }

    @Override
    @Transactional
    public void update(Role object) {
        Validate.notNull(object, "Null role object not allowed");
        Validate.isTrue(object.getId() > 0, "Positive id expected");
        Validate.notEmpty(object.getName(), "Null or empty name not allowed");
        RoleEntity entity = roleDAO.find(object.getId());
        Validate.isTrue(entity != null, "The id doesn't exist");
        entity.setName(object.getName());
        roleDAO.update(entity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Validate.isTrue(id > 0, "Positive id expected");
        RoleEntity entity = roleDAO.find(id);
        Validate.isTrue(entity != null, "The id doesn't exist");
        // check if there are users with this role
        List<User> list = roleDAO.findUsers(id);
        Validate.isTrue(list.isEmpty(), "There are users having the role id " + id);
        roleDAO.delete(entity);
    }

    @Override
    @Transactional
    public List<User> findUsers(long id) {
        return roleDAO.findUsers(id);
    }
}
