package com.estonia.weatherservice.exception;


import com.estonia.weatherservice.exception.types.ApiRequestException;
import com.estonia.weatherservice.exception.types.NoContentException;
import com.estonia.weatherservice.exception.types.ResourceNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private final Log logger = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                e,
                status,
                ZonedDateTime.now(ZoneId.of("Europe/Tallinn")),
                request.getDescription(false));
        logger.error(errorMessage);
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<ErrorMessage> handleNoContentException(NoContentException e, WebRequest request) {
        final HttpStatus status = HttpStatus.NO_CONTENT;
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                e,
                status,
                ZonedDateTime.now(ZoneId.of("Europe/Tallinn")),
                request.getDescription(false));
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<ErrorMessage> handleApiRequestException(ApiRequestException e, WebRequest request) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                e,
                status,
                ZonedDateTime.now(ZoneId.of("Europe/Tallinn")),
                request.getDescription(false));
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> handleAnyException(Exception e, WebRequest request) {
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                e,
                status,
                ZonedDateTime.now(ZoneId.of("Europe/Tallinn")),
                request.getDescription(false));
        return new ResponseEntity<>(errorMessage, status);
    }


}
