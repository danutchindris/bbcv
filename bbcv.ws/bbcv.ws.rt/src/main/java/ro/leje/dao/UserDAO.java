package ro.leje.dao;


import ro.leje.model.entity.UserEntity;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
public interface UserDAO {

    Long create(UserEntity userEntity);

    List<UserEntity> findAllUsers();

    User findByUserName(String userName);

    User findById(long id);
}
