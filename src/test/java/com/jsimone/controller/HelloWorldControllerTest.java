package com.jsimone.controller;

import com.jsimone.ControllerTestBase;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HelloWorldControllerTest extends ControllerTestBase {

    private static String CONTENT_TYPE = "text/plain;charset=UTF-8";

    @Test
    public void test1() throws Exception {
        String url = "http://localhost:" + port + "/hello/tom";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifySuccessResponse(response, HttpStatus.OK, CONTENT_TYPE, "hello tom");
    }

    @Test
    public void test2() throws Exception {
        String url = "http://localhost:" + port + "/hello2?name=fred";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifySuccessResponse(response, HttpStatus.OK, CONTENT_TYPE, "hello fred");
    }
}





