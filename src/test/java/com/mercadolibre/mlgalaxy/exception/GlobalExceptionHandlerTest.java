package com.mercadolibre.mlgalaxy.exception;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Mock
    WebRequest mockRequest;

    private final String PARAMETER_NAME = "dia";
    private final String RESOURCE_NOT_PRESENT = "The resource is not present";

    @Test
    public void illegalArgumentExceptionHandlerTest() {
        ResponseEntity<Object> response = handler.handleIllegalArgument(new IllegalArgumentException(PARAMETER_NAME), mockRequest);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode().is4xxClientError(), is(Boolean.TRUE));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().toString(), containsString(PARAMETER_NAME));
    }

    @Test
    public void ResourceNotFoundExceptionHandlerTest() {
        ResponseEntity<Object> response = handler.handleResourceNotFound(new ResourceNotFoundException(), mockRequest);
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode().is4xxClientError(), is(Boolean.TRUE));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(response.getBody().toString(), containsString(RESOURCE_NOT_PRESENT));
    }

    @Test
    public void exceptionTest() {
        final Map<String, Object> response = handler.handle(new Exception());
        assertThat(response, notNullValue());
        assertThat(response.get(GlobalExceptionHandler.ERROR_KEY), notNullValue());
    }
}
