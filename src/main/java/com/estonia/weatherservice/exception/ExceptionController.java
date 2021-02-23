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


/**
 * Exception Controller
 * for all Controllers in
 * the application
 * uses - @ControllerAdvice
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 2.0
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private final Log logger = LogFactory.getLog(ExceptionController.class);


    /**
     * this method handles Resource not found Exception
     *
     * @param e       - ResourceNotFoundException
     * @param request - the API request made
     * @return ErrorMessage - ErrorMessage for no-resource found
     * @author Abdul Wahab
     * @since 1.0
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return getErrorMessageResponseEntity(e, request, status);
    }

    /**
     * this method handles No Content Exception
     * not utilized for now, may be used later
     *
     * @param e       - NoContentException
     * @param request - the API request made
     * @return ErrorMessage - ErrorMessage for no-content found case
     * @author Abdul Wahab
     * @since 1.0
     */
    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<ErrorMessage> handleNoContentException(NoContentException e, WebRequest request) {
        final HttpStatus status = HttpStatus.NO_CONTENT;
        return getErrorMessageResponseEntity(e, request, status);
    }

    /**
     * this method handles Api Request Exception
     * maybe used if the uri/body of request is malformed
     * not utilized for now, may be used later
     *
     * @param e       - ApiRequestException
     * @param request - the API request made
     * @return ErrorMessage - ErrorMessage for bad api request
     * @author Abdul Wahab
     * @since 1.0
     */
    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<ErrorMessage> handleApiRequestException(ApiRequestException e, WebRequest request) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return getErrorMessageResponseEntity(e, request, status);
    }

    /**
     * this method handles a generic exception unknown previously
     *
     * @param e       - Generic Exception - Unknown type
     * @param request - the API request made
     * @return ErrorMessage - ErrorMessage for unknown error case
     * @author Abdul Wahab
     * @since 1.0
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> handleAnyException(Exception e, WebRequest request) {
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getErrorMessageResponseEntity(e, request, status);
    }


    /**
     * this is a helper function for the above Exception Handling functions
     * for Code Refactoring
     *
     * @param e       - Exception
     * @param request - API Request URI
     * @param status  - designated Http Status code
     * @return ResponseEntity<ErrorMessage>
     * @since 1.0
     */
    private ResponseEntity<ErrorMessage> getErrorMessageResponseEntity(Exception e, WebRequest request, HttpStatus status) {
        final String timeNow = ZonedDateTime.now(ZoneId.of("Europe/Tallinn")).toString();
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                e,
                status,
                timeNow,
                request.getDescription(false));
        logger.error(request + "\n" + timeNow, e);
        return new ResponseEntity<>(errorMessage, status);
    }


}
