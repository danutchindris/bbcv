package ro.leje.util.validator;

import org.springframework.context.i18n.LocaleContextHolder;
import ro.leje.model.vo.Image;
import ro.leje.service.ImageService;
import ro.leje.util.annotation.UniqueCover;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since March 26, 2017
 */
public class UniqueCoverValidator implements ConstraintValidator<UniqueCover, Image> {

    @Resource
    private ImageService imageService;

    public UniqueCoverValidator() {
    }

    public void initialize(UniqueCover constraint) {
    }

    public boolean isValid(Image image, ConstraintValidatorContext context) {
        final String language = LocaleContextHolder.getLocale().getLanguage();
        final Optional<Long> articleId = imageService.findArticle(image.getId());
        final Optional<Image> cover = articleId.flatMap(id -> imageService.findCover(id, language));
        return !image.getCover() || !cover.isPresent();
    }
}
