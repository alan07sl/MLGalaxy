package com.mercadolibre.mlgalaxy.exception;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Mock
    WebRequest mockRequest;

    private final String PARAMETER_NAME = "dia";
    private final String RESOURCE_NOT_PRESENT = "The resource is not present";

    @Test
    public void illegalArgumentExceptionHandlerTest() {
        ResponseEntity<Object> response = handler.handleIllegalArgument(new IllegalArgumentException(PARAMETER_NAME), mockRequest);
        assertNotNull(response);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertTrue(response.getBody().toString().contains(PARAMETER_NAME));
    }

    @Test
    public void ResourceNotFoundExceptionHandlerTest() {
        ResponseEntity<Object> response = handler.handleResourceNotFound(new ResourceNotFoundException(), mockRequest);
        assertNotNull(response);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertTrue(response.getBody().toString().contains(RESOURCE_NOT_PRESENT));
    }

    @Test
    public void exceptionTest() {
        final Map<String, Object> response = handler.handle(new Exception());
        assertNotNull(response);
        assertNotNull(response.get(GlobalExceptionHandler.ERROR_KEY));
    }
}
