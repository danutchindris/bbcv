package ro.leje.util;

/**
 * @author Danut Chindris
 * @since August 12, 2015
 */
public interface RestMappings {

    public static final String USER = "/user";

    // we are using a regular expression for the userName path variable lest the value gets truncated
    // for instance, if the path variable is declared as {userName}
    // for "test.user" the value gets truncated to "test"
    public static final String USER_FIND_BY_USERNAME = "/findByUserName/{userName:.+}";

    public static final String USER_FIND_ALL = "/findAll";
}
