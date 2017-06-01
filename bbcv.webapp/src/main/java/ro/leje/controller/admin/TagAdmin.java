package ro.leje.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Tag;
import ro.leje.service.TagService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author Danut Chindris
 * @since May 3, 2017
 */
@Controller
public class TagAdmin extends AbstractAdmin {

    @Resource
    private TagService tagService;

    @RequestMapping("/tags")
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public String display(final Model model,
                          final @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute("types", Arrays.asList("continent", "country", "destination", "tag"));
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return "admin/tags";
    }

    @RequestMapping("/tags/json")
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public
    @ResponseBody
    List<Tag> find() {
        return tagService.find();
    }

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public
    @ResponseBody
    String create(@RequestBody @Valid Tag tag) {
        return tagService.create(tag).orElse("item.not.created");
    }

    @RequestMapping(value = "/tag", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public
    @ResponseBody
    String update(@RequestBody @Valid Tag tag) {
        return tagService.update(tag).orElse("item.not.updated");
    }

    @RequestMapping(value = "/tag", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public
    @ResponseBody
    String delete(@RequestParam Long tagId) {
        return tagService.delete(tagId).orElse("item.not.deleted");
    }
}
