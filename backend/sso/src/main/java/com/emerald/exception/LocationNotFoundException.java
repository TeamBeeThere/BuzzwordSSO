package com.emerald.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(int id) {
        super("Location not found with id: " + id);
    }
}
