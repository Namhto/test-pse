package com.namhto.mowitnow;

public enum ErrorCode {
    CONFIGURATION_CANNOT_READ_FILE("An error occurred while reading the configuration file"),
    CONFIGURATION_EMPTY_CONFIGURATION_FILE("Empty configuration file"),
    CONFIGURATION_INVALID_NORTH_EAST_LIMIT_POSITION("Invalid north-east limit position"),
    CONFIGURATION_POSITION_NOT_A_NUMBER("Invalid position value"),
    CONFIGURATION_INVALID_INITIAL_POSITION_AND_ORIENTATION("Invalid initial position and orientation"),
    CONFIGURATION_INVALID_ORIENTATION("Invalid orientation"),
    CONFIGURATION_INVALID_INSTRUCTION("Invalid instruction"),

    INVALID_POSITION("Invalid position with negative coordinates"),
    POSITION_OUTSIDE_LIMITS("Position can not be outside of the terrain limits"),
    ;

    private String value;

    private Object data;

    ErrorCode(String value) {
        this.value = value;
    }

    public ErrorCode withData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return data != null ? String.format(value + ": %s", data) : value;
    }
}
