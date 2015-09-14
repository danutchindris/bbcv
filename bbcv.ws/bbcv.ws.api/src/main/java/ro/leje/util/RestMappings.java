package ro.leje.util;

/**
 * @author Danut Chindris
 * @since August 12, 2015
 */
public interface RestMappings {

    String USER = "/user";

    String FIND_ALL = "/findAll";

    // we are using a regular expression for the userName path variable lest the value gets truncated
    // for instance, if the path variable is declared as {userName}
    // for "test.user" the value gets truncated to "test"
    String USER_FIND_BY_USER_NAME = "/findByUserName/{userName:.+}";

    String USER_FIND_BY_ID = "/findById/{id}";

    String USER_FIND_ROLES = "/role/{id}";

    String ROLE = "/role";
}
