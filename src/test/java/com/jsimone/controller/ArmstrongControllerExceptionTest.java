package com.jsimone.controller;

import com.jsimone.ControllerTestBase;
import com.jsimone.constant.UrlPath;
import com.jsimone.error.FieldError;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ArmstrongControllerExceptionTest extends ControllerTestBase {

    @Test
    public void test1() {
        String url = "http://localhost:" + port + UrlPath.URL_ARMSTRONG_NUMBERS;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifyErrorResponse(response, HttpStatus.BAD_REQUEST, HttpMethod.GET, "org.springframework.validation.BeanPropertyBindingResult: 2 errors");
        verifyFieldErrors(2, response,
                makeList(new FieldError("start", null, "start must be a positive number or 0"),
                        new FieldError("end", null, "end must be a positive number or 0")));
    }

}
