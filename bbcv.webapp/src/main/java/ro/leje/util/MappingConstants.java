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
    String LINKS = "/links";
    String CONTACT_US = "/contact-us";

    // authentication and authorization related constants
    String LOGIN = "/login";

    // administration related constants
    String ADMIN = "/admin";

    String USER = "/user";
    String USER_LIST = "/user/list";
    String USER_LIST_JSON = "/user/list/json";
    String USER_ROLE = "/user/{userId}/role/{roleId}";
    String USER_ROLE_LIST = "/user/{id}/role/list";
    String USER_ROLE_LIST_JSON = "/user/{id}/role/list/json";

    String ROLE = "/role";
    String ROLE_LIST = "/role/list";
    String ROLE_LIST_JSON = "/role/list/json";

    String LINK = "/link";
    String LINK_LIST = "/link/list";
    String LINK_LIST_JSON = "/link/list/json";

    String ARTICLE = "/article";
    String ARTICLE_LIST = "/article/list";
    String ARTICLE_LIST_JSON = "/article/list/json";
    String ARTICLE_LANGUAGE = "/article/language";
    String ARTICLE_IMAGE_LIST = "/article/{id}/image/list";
    String ARTICLE_IMAGE_LIST_JSON = "/article/{id}/image/list/json";
    String ARTICLE_IMAGE_LIST_UPLOAD_FILE = "/article/{id}/uploadFile";
    String ARTICLE_IMAGE_LANGUAGE = "/image/language";
    String ARTICLE_IMAGE = "/article/{id}/image";

    String MESSAGE = "/message";
    String MESSAGE_LIST = "/message/list";
    String MESSAGE_LIST_JSON = "/message/list/json";

    // shop related constants
    String SHOP = "/shop";

    // articles related constants
    String ARTICLES = "/articles";
    String ARTICLE_DETAILS = "/{articleId}/*";
}
