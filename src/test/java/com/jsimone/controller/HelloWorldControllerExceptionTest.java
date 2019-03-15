package com.jsimone.controller;

import com.jsimone.app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerExceptionTest {

    private static String CONTENT_TYPE = "text/plain;charset=UTF-8";

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void test1() throws Exception {
        String url = "http://localhost:" + port + "/hello/tom";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(CONTENT_TYPE, response.getHeaders().getContentType().toString());
        assertEquals("hello tom", response.getBody());
    }

    @Test
    public void test2() throws Exception {
        String url = "http://localhost:" + port + "/hello2?name=fred";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(CONTENT_TYPE, response.getHeaders().getContentType().toString());
        assertEquals("hello fred", response.getBody());
    }
}





