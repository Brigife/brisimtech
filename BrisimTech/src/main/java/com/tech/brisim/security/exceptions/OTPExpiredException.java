package com.tech.brisim.security.exceptions;

public class OTPExpiredException extends RuntimeException {

    public OTPExpiredException(String message) {
        super(message);
    }
}