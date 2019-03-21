package com.jsimone;

import com.jsimone.app.Application;
import com.jsimone.error.ErrorResponse;
import com.jsimone.error.FieldError;
import com.jsimone.util.JsonUtil;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTestBase {

    protected final static String CONTENT_TYPE = "Content-Type";
    protected final static String EXPECTED_MEDIA_TYPE = "[" + MediaType.APPLICATION_JSON_UTF8.toString() + "]";

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate = new TestRestTemplate();

    protected HttpEntity<String> buildHttpEntity(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8.toString());
        return new HttpEntity<String>(json, headers);
    }

    protected void verifySuccessResponse(ResponseEntity<String> responseEntity, HttpStatus expectedStatus, MediaType expectedMediaType,
                                              String expectedResponseBody) {
        assertEquals(expectedResponseBody, responseEntity.getBody());
        assertEquals(expectedStatus.value(), responseEntity.getStatusCodeValue());
        assertEquals(expectedMediaType.toString(), responseEntity.getHeaders().getContentType().toString());
    }

    protected void verifySuccessResponse(ResponseEntity<String> responseEntity, HttpStatus expectedStatus, String expectedContentType,
                                         String expectedResponseBody) {
        assertEquals(expectedResponseBody, responseEntity.getBody());
        assertEquals(expectedStatus.value(), responseEntity.getStatusCodeValue());
        assertEquals(expectedContentType, responseEntity.getHeaders().getContentType().toString());
    }

    protected void verifySuccessResponse(ResponseEntity<String> responseEntity, HttpStatus expectedStatus, MediaType expectedMediaType) {
        assertTrue(responseEntity.getBody().length() > 0);
        assertEquals(expectedStatus.value(), responseEntity.getStatusCodeValue());
        assertEquals(expectedMediaType.toString(), responseEntity.getHeaders().getContentType().toString());
    }

    protected ErrorResponse verifyErrorResponse(ResponseEntity<String> responseEntity, HttpStatus expectedStatus,
                                                HttpMethod expectedMethod,
                                                String expectedMessage) {
        System.out.println(responseEntity.getBody());
        ErrorResponse errorResponse = JsonUtil.fromJson(responseEntity.getBody());

        assertEquals(expectedStatus.value(), errorResponse.getCode());
        assertEquals(expectedMethod.toString(), errorResponse.getMethod());
        assertTrue("message does not start with: "+expectedMessage, errorResponse.getMessage().startsWith(expectedMessage));
        assertEquals(expectedStatus, responseEntity.getStatusCode());
        assertEquals(EXPECTED_MEDIA_TYPE, responseEntity.getHeaders().get(CONTENT_TYPE).toString());

        return errorResponse;
    }

    protected void verifyFieldErrors(int totalFieldErrorsExpected, ResponseEntity<String> responseEntity, List<FieldError> expectedFieldErrors) {
        ErrorResponse errorResponse = JsonUtil.fromJson(responseEntity.getBody());
        assertEquals(totalFieldErrorsExpected, errorResponse.getErrors().size());
        expectedFieldErrors.forEach(efe -> {
            System.out.print("--> found = " + errorResponse.getErrors().contains(efe) + "   " + efe);
            assertTrue(efe + " not found", errorResponse.getErrors().contains(efe));
        });
    }

    protected List<FieldError> makeList(FieldError... errors) {
        return Arrays.asList(errors); //stream(errors).collect(Collectors.toList());
    }
}

