package com.emerald.exception;

import com.emerald.enums.IDTypeEnum;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(IDTypeEnum type, int id) {
        super(String.format("Employee not found with %s id: $d", type.getName(), id));
    }
}