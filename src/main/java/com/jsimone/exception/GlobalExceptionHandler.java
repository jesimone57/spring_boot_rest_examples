package com.jsimone.exception;

import com.jsimone.error.ErrorResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	 * Gracefully handle any other Runtime exceptions by logging the error and returning a 500 with appropriate message
	 * This handles all such errors in one place.
	 *
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<Object> handleDefaultException(RuntimeException exception, WebRequest request) {
		String URIPath = request.getDescription(false).substring(4);
		log.error("Runtime exception encountered for URIPath=" + URIPath + "  exception=" + exception.toString(), exception);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String message = exception.getMessage();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponseBody errorResponseBody = new ErrorResponseBody(status.value(), URIPath, message);

		return handleExceptionInternal(exception, errorResponseBody, headers, status, request);
	}

	/**
	 * Adding these properties will make the following code active:
	 * spring.mvc.throw-exception-if-no-handler-found=true
	 * spring.resources.add-mappings=false
	 *
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String URIPath = request.getDescription(false).substring(4);
		String message = "The URL you have reached is not in service at this time";
		ErrorResponseBody errorResponseBody = new ErrorResponseBody(status.value(), URIPath, message);
		return new ResponseEntity<Object>(errorResponseBody, status);
	}
}





