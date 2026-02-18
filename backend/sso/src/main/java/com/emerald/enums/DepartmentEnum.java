package com.emerald.enums;

public enum DepartmentEnum {
    SALES("Sales"),
    IT("Information Technology"),
    LEGAL("Legal"),
    HR("HR");

    private final String name;

    private DepartmentEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
