package com.user.service.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found:");
    }

    public ResourceNotFoundException(String message) {
        super("Resource not found: " + message);
    }
}
