package ro.leje.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ro.leje.config.Settings;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Dictionary;
import ro.leje.model.vo.Image;
import ro.leje.service.ArticleService;
import ro.leje.service.DictionaryService;
import ro.leje.service.ImageService;
import ro.leje.util.CategoryConstants;
import ro.leje.util.MappingConstants;
import ro.leje.util.PermissionConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

/**
 * @author Danut Chindris
 * @since March 22, 2017
 */
@Controller
public class ImageAdmin extends AbstractAdmin {

    private static final String AUTHENTICATED_USER_FIRST_NAME = "authenticatedUserFirstName";
    private static final String ID = "id";
    private static final String IMAGES = "images";

    @Resource
    private LanguageDelegate languageDelegate;

    @Resource
    private Settings settings;

    @Resource
    private ArticleService articleService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private ImageService imageService;

    @RequestMapping(value = MappingConstants.ARTICLE_IMAGE_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ARTICLE_LIST + "')")
    public String displayArticleImageList(@PathVariable long id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(ID, id);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.ARTICLE_IMAGE_LIST;
    }

    @RequestMapping(MappingConstants.ARTICLE_IMAGE_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ARTICLE_LIST + "')")
    public @ResponseBody
    List<Image> findArticleImages(@PathVariable long id, Locale locale) {
        return articleService.findImages(id, locale.getLanguage());
    }

    @RequestMapping(value = MappingConstants.ARTICLE_IMAGE_LIST_UPLOAD_FILE, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public @ResponseBody Long uploadFile(@PathVariable long id, @RequestParam("file") MultipartFile file) {
        try {
            // get the file name and build the local file path
            String filePath = Paths.get(settings.getImagesLocation() + id + "/",
                    file.getOriginalFilename()).toString();
            // save the file locally
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(file.getBytes());
            stream.close();
        }
        catch (Exception e) {
            // handle exception
        }
        // create the image record in the database
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        Long imageId = imageService.create(image, id);
        // create the dictionary record in the database
        Dictionary dictionary = new Dictionary();
        dictionary.setObjectId(imageId);
        dictionary.setObjectType(CategoryConstants.IMAGE_TYPE);
        dictionary.setCategory(CategoryConstants.TITLE);
        dictionary.setEn(CategoryConstants.DEFAULT_TITLE_EN);
        dictionary.setRo(CategoryConstants.DEFAULT_TITLE_RO);
        dictionaryService.create(dictionary);
        return imageId;
    }

    @RequestMapping(value = MappingConstants.ARTICLE_IMAGE_LANGUAGE, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public @ResponseBody String changeImageTitleLanguage(Model model, Locale locale, @RequestBody Long imageId) {
        if (imageId != null) {
            // get the title translation for the current language
        }
        // return the translation
        return "";
    }
}
