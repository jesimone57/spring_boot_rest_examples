package com.jsimone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsimone.app.Application;
import com.jsimone.error.ErrorResponseBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerExceptionTest {

    private static String CONTENT_TYPE = "application/json;charset=UTF-8";

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void test1() throws Exception {
        String url = "http://localhost:" + port + "/hello/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(CONTENT_TYPE, response.getHeaders().getContentType().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = response.getBody();
        ErrorResponseBody errorResponse = objectMapper.readValue(json, ErrorResponseBody.class);
        assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getCode());
        assertEquals("/hello/", errorResponse.getPath());
        assertEquals("The URL you have reached is not in service at this time", errorResponse.getMessage());
        assertTrue(errorResponse.getErrors().isEmpty());
    }


}





