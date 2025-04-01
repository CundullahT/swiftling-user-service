package com.swiftling.exception;

public class UserCanNotBeDeletedException extends RuntimeException {

    public UserCanNotBeDeletedException(String message) {
        super(message);
    }

}
