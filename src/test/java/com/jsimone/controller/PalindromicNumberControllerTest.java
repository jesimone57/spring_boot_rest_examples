package com.jsimone.controller;

import com.jsimone.ControllerTestBase;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


public class PalindromicNumberControllerTest extends ControllerTestBase {

    @Test
    public void test1() throws Exception {
        String url = "http://localhost:" + port + "/palindromes/1/10";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifySuccessResponse(response, HttpStatus.OK, MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void test2() throws Exception {
        String url = "http://localhost:" + port + "/palindromes?start=1&end=10";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        verifySuccessResponse(response, HttpStatus.OK, MediaType.APPLICATION_JSON_UTF8);
    }
}