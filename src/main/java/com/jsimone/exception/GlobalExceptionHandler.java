package com.jsimone.exception;

import com.jsimone.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return buildBadRequestResponse(ex, webRequest);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest webRequest) {
        ResponseEntity<Object> responseEntity = null;
        if (exception instanceof ErrorResponseException) {
            responseEntity = buildBadRequestResponse(exception, webRequest);
        } else if (exception instanceof RangeValidationException) {
            responseEntity = buildBadRequestResponse(exception, webRequest);
        } else {
            responseEntity = buildInteralRuntimeException(exception, webRequest);
        }
        return responseEntity;
    }

    private ResponseEntity<Object> buildBadRequestResponse(Exception exception, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = null;
        if (exception instanceof ErrorResponseException) {
            errorResponse = ((ErrorResponseException) exception).getErrorResponse();
        } else {
            errorResponse = new ErrorResponse(status.value(), webRequest, exception);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(errorResponse, headers, status);
    }

    /**
     * Gracefully handle any other Runtime exceptions by logging the error and returning a 500 with appropriate message
     * This handles all such errors in one place.
     *
     * @param exception
     * @param webRequest
     * @return
     */
    private ResponseEntity<Object> buildInteralRuntimeException(Exception exception, WebRequest webRequest) {
        String uriPath = webRequest.getDescription(false).substring(4);
        String method = ((ServletWebRequest) webRequest).getRequest().getMethod();
        log.error("Runtime exception encountered for URIPath=" + uriPath + "  exception=" + exception.toString(), exception);

        String message = exception.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), uriPath, method, message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errorResponse, headers, status);
    }
}





