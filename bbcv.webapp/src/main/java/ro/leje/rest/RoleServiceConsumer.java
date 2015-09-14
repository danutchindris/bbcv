package ro.leje.rest;

import ro.leje.model.vo.Role;

import java.util.List;

/**
 * @author Danut Chindris
 * @since September 14, 2015
 */
public interface RoleServiceConsumer {

    List<Role> findAll();
}
