package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.dao.RoleDAO;
import ro.leje.model.entity.RoleEntity;
import ro.leje.model.entity.UserEntity;
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
}
