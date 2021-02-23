package com.estonia.weatherservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * represents an error message
 *
 * @author Abdul Wahab
 * @version 1.0
 * @since 2.0
 */
@Data
public class ErrorMessage {
    private final HttpStatus httpStatus;
    private final String timestamp;
    private final String description;
    public String message;

    /**
     * this method constructs an error
     *
     * @param throwable - Not utilised for now will be used later for server side logging
     * @return ErrorMessage - ErrorMessage customized to parameters
     * @author Abdul Wahab
     * @since 1.0
     */
    public ErrorMessage(String message, Throwable throwable, HttpStatus httpStatus, String timestamp, String description) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.description = description;
    }
}
