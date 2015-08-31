package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.entity.RoleEntity;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
@Repository
public class RoleDAOImpl extends BaseDAOImpl implements RoleDAO {

    @Override
    public List<RoleEntity> findAll() {
        return getCurrentSession().createQuery("from ro.leje.model.entity.RoleEntity").list();
    }
}
