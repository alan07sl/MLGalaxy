package com.mercadolibre.mlgalaxy.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Map;

/**
 * Single global error handling component A.K.A. Exception handler
 *
 * Benefits of this approach:
 *
 * Allows full control over the body of the response as well as the status code
 * Allows mapping of several exceptions to the same method, to be handled together
 * Makes good use of the newer RESTful ResposeEntity response
 *
 * Returns a {@link Map} with an "Error" key and the error message as value for its key.
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    protected static final String ERROR_KEY = "Error";
    private final String ERROR_DEFAULT_MESSAGE = "An error has occurred";
    private final String REQUIRED_PARAMETER_NOT_PRESENT_ERROR_MESSAGE = "A required parameter is missing: ";
    private final String RESOURCE_NOT_FOUND = "The resource is not present";

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleIllegalArgument(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = REQUIRED_PARAMETER_NOT_PRESENT_ERROR_MESSAGE + "\"" + ex.getLocalizedMessage() + "\"";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = RESOURCE_NOT_FOUND;
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Handler por defecto.
     *
     * @param exception
     *            {@link Throwable} Una excepcion no contemplada.
     * @return {@link Map} El mapa con la key "error" y el mensaje como valor.
     */
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handle(final Throwable exception) {
        LOGGER.debug("", exception);
        return error(ERROR_DEFAULT_MESSAGE);
    }

    /**
     * Crea el {@link Map} de respuesta.
     *
     * @param message
     *            {@link String} El mensaje de error a retornar.
     * @return {@link Map} El mapa con la key "error" y el mensaje como valor.
     */
    private static Map<String, Object> error(final String message) {
        return Collections.singletonMap(ERROR_KEY, message);
    }
}
