package ro.leje.dao;


import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
public interface UserDAO {

    Long create(ro.leje.model.entity.User user);

    List<ro.leje.model.entity.User> findAllUsers();

    ro.leje.model.vo.User findByUserName(String userName);

    ro.leje.model.vo.User findById(long id);
}
