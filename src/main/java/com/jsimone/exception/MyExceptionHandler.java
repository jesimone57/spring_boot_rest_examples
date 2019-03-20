package com.jsimone.exception;

import com.jsimone.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class MyExceptionHandler extends ResponseEntityExceptionHandler {
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
    protected ResponseEntity<Object> handleDefaultException(RuntimeException exception, HttpServletRequest request, WebRequest webRequest) {
        String uriPath = webRequest.getDescription(false).substring(4);
        log.error("Runtime exception encountered for URIPath=" + uriPath + "  exception=" + exception.toString(), exception);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String message = exception.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), uriPath, request.getMethod(), message);

        return handleExceptionInternal(exception, errorResponse, headers, status, webRequest);
    }



}
