package com.emerald.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(int id) {
        super("Department not found with id: " + id);
    }
}
