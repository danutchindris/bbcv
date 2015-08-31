package ro.leje.dao;

import ro.leje.model.entity.RoleEntity;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
public interface RoleDAO {

    List<RoleEntity> findAll();
}
