package com.estonia.weatherservice.exception.types;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException() {
        super("Api Request Exception");
    }
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
