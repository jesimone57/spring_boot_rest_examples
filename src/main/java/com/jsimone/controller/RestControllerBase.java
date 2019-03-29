package com.jsimone.controller;

import com.jsimone.error.ErrorResponse;
import com.jsimone.exception.ErrorResponseException;
import com.jsimone.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

public class RestControllerBase {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Gracefully handle any other Runtime exceptions by logging the error and returning a 500 with appropriate message
     * This handles all such errors in one place.
     *
     * @param exception
     * @param request
     * @return
     */

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleDefaultException(Exception exception, HttpServletRequest request, WebRequest webRequest) {
        String uriPath = webRequest.getDescription(false).substring(4);
        log.error("Runtime exception encountered for URIPath=" + uriPath + "  exception=" + exception.toString(), exception);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String message = exception.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), uriPath, request.getMethod(), message);

        return new ResponseEntity<>(JsonUtil.toJson(errorResponse), headers, status);
    }

    @ExceptionHandler({BindException.class})
    protected ResponseEntity<Object> handleBindException(BindException exception, HttpServletRequest request) {
        return buildBadRequestResponse(exception, request);
    }


    @ExceptionHandler({ErrorResponseException.class})
    protected ResponseEntity<Object> handleBindException(ErrorResponseException exception, HttpServletRequest request) {
        return buildBadRequestResponse(exception, request);
    }


    private ResponseEntity<Object> buildBadRequestResponse(Exception exception, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = null;
        if (exception instanceof ErrorResponseException) {
            errorResponse = ((ErrorResponseException) exception).getErrorResponse();
        } else {
            errorResponse = new ErrorResponse(status.value(), request, exception);
        }
        return new ResponseEntity<>(JsonUtil.toJson(errorResponse), headers, status);
    }

}
