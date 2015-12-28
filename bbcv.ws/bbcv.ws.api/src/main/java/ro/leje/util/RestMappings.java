package ro.leje.util;

/**
 * @author Danut Chindris
 * @since August 12, 2015
 */
public interface RestMappings {

    String API = "/api";

    String V1 = "/v1";

    String USERS = "/users";

    String ROLES = "/roles";

    String PERMISSIONS = "/permissions";

    // we are using a regular expression for the userName path variable lest the value gets truncated
    // for instance, if the path variable is declared as {userName}
    // for "test.user" the value gets truncated to "test"
    String USER_NAME = "/{userName:.+}";

    String USER_ID = "/{userId}";

    String ROLE_ID = "/{roleId}";
}
