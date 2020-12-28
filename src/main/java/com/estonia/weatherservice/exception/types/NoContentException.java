package com.estonia.weatherservice.exception.types;

public class NoContentException extends RuntimeException{
    public NoContentException() {
        super("No Content Found here");
    }
    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(Throwable cause) {
        super("No Content Found", cause);
    }
}
