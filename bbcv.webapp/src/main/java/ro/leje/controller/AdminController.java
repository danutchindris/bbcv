package ro.leje.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.vo.User;
import ro.leje.rest.UserServiceConsumer;
import ro.leje.util.ErrorMessage;
import ro.leje.util.ValidationResponse;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Danut Chindris
 * @since August 15, 2015, Catiasu
 */
@Controller
@RequestMapping(value = MappingConstants.ADMIN)
public class AdminController {

    private static final String USERS = "users";

    @Resource
    private MessageSource messages;

    @Resource
    private LanguageDelegate languageDelegate;

    @Resource
    private UserServiceConsumer userServiceConsumer;

    @Resource
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = MappingConstants.USER_LIST, method = RequestMethod.GET)
    public String displayUserList(Model model) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_LIST;
    }

    @RequestMapping(MappingConstants.USER_LIST_JSON)
    public @ResponseBody List<User> findUsers() {
        return userServiceConsumer.findAll();
    }

    // TODO
    // http://www.programmingforfuture.com/2013/05/spring-mvc-ajax-based-form-process.html
    // http://dtr-trading.blogspot.ro/2014/04/spring-mvc-4-spring-security-32-part-3.html

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
                String[] resolvedMessageCodes = bindingResult.resolveMessageCodes(fieldError.getCode());
                String resolvedMessageCode = resolvedMessageCodes[0];
                String message = messages.getMessage(resolvedMessageCode + "."
                        + fieldError.getField(), new Object[]{fieldError.getRejectedValue()}, null);
                //errorMessages.add(fieldError.getField(), message);
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
}
