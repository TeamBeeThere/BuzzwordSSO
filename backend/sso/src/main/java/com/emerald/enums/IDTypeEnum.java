package com.emerald.enums;

public enum IDTypeEnum {
    EMPLOYEE("employee"),
    USER("user");

    private final String name;

    private IDTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
