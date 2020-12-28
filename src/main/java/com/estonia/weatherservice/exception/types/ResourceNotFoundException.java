package com.estonia.weatherservice.exception.types;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("This Resource is not available");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }


    public ResourceNotFoundException(Throwable cause) {
        super("This Resource is not available", cause);
    }

}
