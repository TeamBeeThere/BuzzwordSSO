package com.emerald.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User not found with id: " + id);
    }

    public UserNotFoundException(String userName) {
        super("User not found with username: " + userName);
    }
}
