package ro.leje.util;

/**
 * @author Danut Chindris
 * @since July 12, 2015
 */
public interface MappingConstants {

    String ROOT = "/";
    String HOME = "/home";
    String WE_BELIEVE = "/we-believe";
    String YOU_ARE_INVITED = "/you-are-invited";

    // authentication and authorization related constants
    String LOGIN = "/login";

    // administration related constants
    String ADMIN = "/admin";
    String USER = "/user";
    String USER_LIST = "/user/list";
    String USER_ROLE_LIST = "/user/{id}/role/list";
    String USER_LIST_JSON = "/user/list/json";
    String USER_ROLE_LIST_JSON = "/user/{id}/role/list/json";
    String ROLE = "/role";
    String ROLE_LIST = "/role/list";
    String ROLE_LIST_JSON = "/role/list/json";

    // shop related constants
    String SHOP = "/shop";
}
