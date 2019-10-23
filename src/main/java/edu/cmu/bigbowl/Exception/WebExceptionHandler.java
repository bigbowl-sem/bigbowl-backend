package edu.cmu.bigbowl.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class WebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler
    public String methodArgumentNotValid(BindException e) {
        log.error("methodArgumentNotValid", e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 0; i < allErrors.size(); i++) {
            ObjectError error = allErrors.get(i);
            errorMessage.append(error.getDefaultMessage());
            if (i != allErrors.size() - 1) {
                errorMessage.append(", ");
            }
        }
        // do something
        return generateErrorInfo(1, errorMessage.toString(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    public String entityNotFoundException(AccountNotFoundException e) {
        log.error("entity not found: ", e);
        return generateErrorInfo(-1, "entity not found");
    }

    @ExceptionHandler
    public String unknownException(Exception e) {
        log.error("unknown exception: ", e);
        return generateErrorInfo(-99, "unknown exception!");
    }


    /**
     * Put error msg to request
     * @param code
     * @param message
     * @param httpStatus
     * @return
     */
    private String generateErrorInfo(int code, String message, int httpStatus) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("code", code);
        request.setAttribute("message", message);
        request.setAttribute("javax.servlet.error.status_code", httpStatus);
        return "forward:/error";
    }

    private String generateErrorInfo(int code, String message) {
        return generateErrorInfo(code, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
