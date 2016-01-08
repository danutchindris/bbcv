package ro.leje.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ro.leje.util.ValidationResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Danut Chindris
 * @since October 19, 2015
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationResponse processValidationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ValidationResponse processFieldErrors(List<FieldError> fieldErrors) {
        ValidationResponse validationResponse = new ValidationResponse();
        for (FieldError fieldError: fieldErrors) {
            validationResponse.addErrorMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return validationResponse;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(HttpServletRequest request, Exception exception) {
        logger.error("Request: " + request.getRequestURI() + " raised " + exception);
        return "access-denied";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception exception) throws Exception {
        // rethrow annotated exceptions or they will be processed here instead
        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null) {
            throw exception;
        }
        logger.error("Request: " + request.getRequestURI() + " raised " + exception);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("default-error");
        return mav;
    }
}
