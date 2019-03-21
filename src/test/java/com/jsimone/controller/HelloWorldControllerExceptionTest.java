package com.jsimone.controller;

import com.jsimone.ControllerTestBase;
import com.jsimone.error.ErrorResponse;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertTrue;

public class HelloWorldControllerExceptionTest extends ControllerTestBase {

    @Test
    public void test1() throws Exception {
        String url = "http://localhost:" + port + "/hello/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ErrorResponse er = verifyErrorResponse(response, HttpStatus.NOT_FOUND, HttpMethod.GET, "The URL you have reached is not in service at this time");
        assertTrue(er.getErrors().isEmpty());
    }

}
