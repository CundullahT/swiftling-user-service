package com.swiftling.enums;

public enum Status {

    ENABLED("Enabled"), DISABLED("Disabled"), DELETED("Deleted");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
