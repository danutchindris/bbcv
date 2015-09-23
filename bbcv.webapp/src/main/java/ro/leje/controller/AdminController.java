package ro.leje.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;
import ro.leje.rest.RoleServiceConsumer;
import ro.leje.rest.UserServiceConsumer;
import ro.leje.util.ErrorMessage;
import ro.leje.util.MappingConstants;
import ro.leje.util.ValidationResponse;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 15, 2015, Catiasu
 */
@Controller
@RequestMapping(value = MappingConstants.ADMIN)
@PreAuthorize("hasRole('admin')")
public class AdminController {

    private static final String ID = "id";
    private static final String ROLES = "roles";

    @Resource
    private LanguageDelegate languageDelegate;

    @Resource
    private UserServiceConsumer userServiceConsumer;

    @Resource
    private RoleServiceConsumer roleServiceConsumer;

    @Resource
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = MappingConstants.USER_LIST, method = RequestMethod.GET)
    public String displayUserList(Model model) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_LIST;
    }

    @RequestMapping(value = MappingConstants.USER_ROLE_LIST, method = RequestMethod.GET)
    public String displayUserRoleList(@PathVariable long id, Model model) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(ID, id);
        model.addAttribute(ROLES, roleServiceConsumer.findAll());
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_ROLE_LIST;
    }

    @RequestMapping(MappingConstants.USER_LIST_JSON)
    public @ResponseBody List<User> findUsers() {
        return userServiceConsumer.findAll();
    }

    @RequestMapping(MappingConstants.USER_ROLE_LIST_JSON)
    public @ResponseBody List<Role> findUserRoles(@PathVariable long id) {
        return userServiceConsumer.findRoles(id);
    }

    @RequestMapping(MappingConstants.ROLE_LIST_JSON)
    public @ResponseBody List<Role> findRoles() {
        return roleServiceConsumer.findAll();
    }

    @RequestMapping(value = MappingConstants.USER, method = RequestMethod.POST)
    public @ResponseBody ValidationResponse addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (user != null && user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        ValidationResponse validationResponse = new ValidationResponse();
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ErrorMessage> errorMessages = new ArrayList<>();
            for (FieldError fieldError: fieldErrors) {
                errorMessages.add(new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()));
            }
            validationResponse.setErrorMessageList(errorMessages);
            validationResponse.setStatus("FAILURE");
        }
        else {
            userServiceConsumer.create(user);
            validationResponse.setStatus("SUCCESS");
        }
        return validationResponse;
    }

    @RequestMapping(value = MappingConstants.ROLE_LIST, method = RequestMethod.GET)
    public String displayRoleList(Model model) {
        // model.addAttribute(ROLES, userServiceConsumer.findAll());
        return ViewConstants.ADMIN + "/" + ViewConstants.ROLE_LIST;
    }

    @RequestMapping(value = MappingConstants.USER_ROLE, method = RequestMethod.POST)
    public @ResponseBody ValidationResponse addRole(@PathVariable long userId, @PathVariable long roleId) {
        userServiceConsumer.addRole(userId, roleId);
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setStatus("SUCCESS");
        return validationResponse;
    }
}
