package ro.leje.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.leje.model.vo.Role;
import ro.leje.service.RoleService;
import ro.leje.util.RestMappings;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since September 14, 2015
 */
@RestController
@RequestMapping(RestMappings.ROLE)
public class RoleRestController {

    @Resource
    private RoleService roleService;

    @RequestMapping(value = RestMappings.FIND_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findAll() {
        List<Role> list = roleService.findAll();
        return list;
    }
}
