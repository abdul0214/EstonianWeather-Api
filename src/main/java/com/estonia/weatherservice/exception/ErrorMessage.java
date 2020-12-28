package com.estonia.weatherservice.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ErrorMessage {
    public String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private final String description;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
    public String getDescription() {
        return description;
    }

    public ErrorMessage(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp, String description) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.description = description;
    }
}
