package ro.leje.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Danut Chindris
 * @since October 19, 2015
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlingControllerAdvice.class);

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(HttpServletRequest request, Exception exception) {
        logger.error("Request: " + request.getRequestURI() + " raised " + exception);
        return "access-denied";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception exception) throws Exception {
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
