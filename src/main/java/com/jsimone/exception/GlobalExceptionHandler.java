package com.jsimone.exception;

import com.jsimone.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by jsimone on 6/1/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * Adding these properties will make the following code active:
     * spring.mvc.throw-exception-if-no-handler-found=true
     * spring.resources.add-mappings=false
     *
     * @param ex
     * @param headers
     * @param status
     * @param webRequest
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        String uriPath = webRequest.getDescription(false).substring(4);
        String message = "The URL you have reached is not in service at this time";
        String method = ((ServletWebRequest) webRequest).getRequest().getMethod();
        ErrorResponse errorResponse = new ErrorResponse(status.value(), uriPath, method, message);
        return new ResponseEntity<>(errorResponse, status);
    }


}





