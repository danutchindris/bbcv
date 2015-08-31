package ro.leje.service;

import ro.leje.model.vo.Role;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
public interface RoleService {

    List<Role> findAll();
}
