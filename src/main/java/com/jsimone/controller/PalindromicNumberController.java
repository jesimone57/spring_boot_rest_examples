package com.jsimone.controller;

import com.jsimone.entity.NumbersResponse;
import com.jsimone.entity.NumbersType;
import com.jsimone.entity.Range;
import com.jsimone.error.ErrorResponse;
import com.jsimone.exception.ErrorResponseException;
import com.jsimone.service.PalindromicNumberService;
import com.jsimone.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.jsimone.constant.UrlPath.URL_PALINDROMIC_NUMBERS;
import static com.jsimone.constant.UrlPath.URL_PALINDROMIC_NUMBERS_IN_RANGE;

@RestController
@Api(value = "Palindromic Numbers API",
        description = "A palindromic number is one which equals itself when reversed such as 12321 or 626 or 11.",
        tags = "Palindromic Numbers API")
@RequestMapping("/")
public class PalindromicNumberController {

    @Autowired
    private PalindromicNumberService palindromicNumberService;

    /**
     * A palindromic number is one which equals itself when reversed such as 12321 or 626 or 11.
     */
    @ApiOperation(value = "Find all palindromic numbers in the given range")
    @GetMapping(value = URL_PALINDROMIC_NUMBERS_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getPalindromicNumbersInRange(@PathVariable int start, @PathVariable int end) {
        NumbersResponse response = new NumbersResponse();
        response.setStart(start);
        response.setEnd(end);
        response.setNumbers(palindromicNumberService.computePalindromicNumbersInRange(start, end), NumbersType.Palindrome);
        return response;
    }

    @ApiOperation(value = "Find all palindromic numbers in the given range")
    @GetMapping(value = URL_PALINDROMIC_NUMBERS, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getPalindromicNumbersInRange2(@Valid Range range) {
        validateRange(range);
        NumbersResponse response = new NumbersResponse();
        response.setStart(range.getStart());
        response.setEnd(range.getEnd());
        response.setNumbers(palindromicNumberService.computePalindromicNumbersInRange(range.getStart(), range.getEnd()), NumbersType.Palindrome);
        return response;
    }

    @ExceptionHandler({BindException.class})
    protected ResponseEntity<Object> handleBindException(BindException exception, HttpServletRequest request) {
        return buildBadRequestResponse(exception, request);
    }


    @ExceptionHandler({ErrorResponseException.class})
    protected ResponseEntity<Object> handleBindException(ErrorResponseException exception, HttpServletRequest request) {
        return buildBadRequestResponse(exception, request);
    }

    private void validateRange(Range range) {
        if (range.getEnd() < range.getStart()) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "/palindromes", "GET",
                    String.format("Invalid range.  start value=%d must be before end value=%d.", range.getStart(), range.getEnd()));
            throw new ErrorResponseException(errorResponse);
        }
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