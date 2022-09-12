package com.example.caref.execption;

public class GaragImageException extends RuntimeException {
    public GaragImageException(String message) { super(message); }

    public GaragImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
