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

    @Override
    public void initialize(final UniqueCover constraint) {
    }

    @Override
    public boolean isValid(final Image image, final ConstraintValidatorContext context) {
        final String language = LocaleContextHolder.getLocale().getLanguage();
        final Optional<Image> cover = imageService.findCover(image.getObjectType(), image.getObjectId(), language);
        return !image.getCover() || cover.map(i -> i.getId() == image.getId()).orElse(true);
    }
}
