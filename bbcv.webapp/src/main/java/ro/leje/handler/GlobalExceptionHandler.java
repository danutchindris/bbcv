package ro.leje.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ro.leje.util.ErrorMessage;
import ro.leje.util.ValidationResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public ValidationResponse processValidationError(final MethodArgumentNotValidException e) {
        final BindingResult bindingResult = e.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        final List<ObjectError> objectErrors = bindingResult.getGlobalErrors();
        return new ValidationResponse(Stream.concat(processFieldErrors(fieldErrors).stream(),
                processObjectErrors(objectErrors).stream())
                .collect(Collectors.toList()));
    }

    private List<ErrorMessage> processFieldErrors(final List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(error -> new ErrorMessage(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    private List<ErrorMessage> processObjectErrors(final List<ObjectError> objectErrors) {
        return objectErrors.stream()
                .map(error -> new ErrorMessage("object", error.getDefaultMessage()))
                .collect(Collectors.toList());
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
