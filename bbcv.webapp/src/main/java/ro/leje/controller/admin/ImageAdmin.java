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
import ro.leje.service.DictionaryService;
import ro.leje.service.ImageService;
import ro.leje.util.CategoryConstants;
import ro.leje.util.MappingConstants;
import ro.leje.util.PermissionConstants;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
    private ImageService imageService;

    /**
     * Displays the images list for an article
     */
    @RequestMapping("/images")
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public String displayImages(@RequestParam Long articleId, Model model,
                                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(ID, articleId);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return "admin/images";
    }

    @RequestMapping("/images/json")
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public @ResponseBody
    List<Image> findImages(@RequestParam Long articleId, Locale locale) {
        return imageService.findImages(Optional.ofNullable(articleId), locale.getLanguage());
    }

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    @PreAuthorize("hasRole('permission_admin_create_article')")
    public @ResponseBody Long uploadFile(@RequestParam Long articleId, @RequestParam("file") MultipartFile file) {
        try {
            // get the file name and build the local file path
            final Path path = Paths.get(settings.getImagesLocation()
                            + File.separator + articleId + File.separator,
                    file.getOriginalFilename());
            // save the file locally
            final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path.toFile()));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            // handle exception
        }
        final Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        final Long imageId = imageService.create(image, articleId);
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
