package com.jsimone.controller;

import com.jsimone.ControllerTestBase;
import com.jsimone.error.FieldError;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class PalindromicNumberControllerExceptionTest extends ControllerTestBase {

    @Test
    public void test1() throws Exception {
        String url = "http://localhost:" + port + "/palindromes/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifyErrorResponse(response, HttpStatus.BAD_REQUEST, HttpMethod.GET, "org.springframework.validation.BeanPropertyBindingResult: 2 errors");
        verifyFieldErrors(2, response,
                makeList(new FieldError("start", null, "start must be a positive number or 0"),
                        new FieldError("end", null, "end must be a positive number or 0")));
    }

    @Test
    public void test2() throws Exception {
        String url = "http://localhost:" + port + "/palindromes?start=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifyErrorResponse(response, HttpStatus.BAD_REQUEST, HttpMethod.GET, "org.springframework.validation.BeanPropertyBindingResult: 1 errors");
        verifyFieldErrors(1, response,
                makeList(new FieldError("end", null, "end must be a positive number or 0")));

    }

    @Test
    public void test3() throws Exception {
        String url = "http://localhost:" + port + "/palindromes?start=a&end=5";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifyErrorResponse(response, HttpStatus.BAD_REQUEST, HttpMethod.GET, "org.springframework.validation.BeanPropertyBindingResult: 1 errors");
        verifyFieldErrors(1, response,
                makeList(new FieldError("start", "a", "Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'start'; nested exception is java.lang.NumberFormatException: For input string: \"a\"")));

    }
}
