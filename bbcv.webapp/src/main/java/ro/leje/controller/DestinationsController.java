package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.Image;
import ro.leje.model.vo.Tag;
import ro.leje.model.vo.User;
import ro.leje.service.ArticleService;
import ro.leje.service.ImageService;
import ro.leje.service.TagService;
import ro.leje.util.Numbers;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since May 22, 2017
 */
@Controller
public class DestinationsController {

    @Resource
    private TagService tagService;

    @Resource
    private ImageService imageService;

    @Resource
    private LanguageDelegate languageDelegate;

    @RequestMapping("/destinations")
    public String getDestinations(final Model model, final Locale locale) {
        final List<Tag> destinations = tagService.findByType(Arrays.asList("continent"));
        final List<Integer> sequence = Numbers.getCardSequence(destinations.size());
        for (int i = 0; i < destinations.size(); i++) {
            destinations.get(i).setCard(sequence.get(i));
        }
        model.addAttribute("destinations", destinations);
        return "destinations";
    }
}
