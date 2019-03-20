package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.PalindromicNumbers;
import com.jsimone.entity.Range;
import com.jsimone.error.ErrorResponse;
import com.jsimone.exception.ErrorResponseException;
import com.jsimone.service.PalindromicNumberService;
import com.jsimone.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class PalindromicNumberController {

    @Autowired
    private PalindromicNumberService palindromicNumberService;

    /**
     * A palindromic number is one which equals itself when reversed such as 12321 or 626 or 11.
     */
    @GetMapping(value = UrlPath.URL_PALINDROMIC_NUMBERS_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PalindromicNumbers getPalindromicNumbersInRange(@PathVariable int start, @PathVariable int end) {
        PalindromicNumbers numbers = new PalindromicNumbers();
        numbers.setStart(start);
        numbers.setEnd(end);
        numbers.setPalindromicNumbers(palindromicNumberService.computePalindromicNumbersInRange(start, end));
        return numbers;
    }

    @GetMapping(value = "/palindromes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PalindromicNumbers getPalindromicNumbersInRange2(@Valid Range range) {
        PalindromicNumbers numbers = new PalindromicNumbers();
        numbers.setStart(range.getStart());
        numbers.setEnd(range.getEnd());
        numbers.setPalindromicNumbers(palindromicNumberService.computePalindromicNumbersInRange(range.getStart(), range.getEnd()));
        return numbers;
    }

    @ExceptionHandler({BindException.class})
    protected ResponseEntity<Object> handleBindException(BindException exception, HttpServletRequest request) {
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