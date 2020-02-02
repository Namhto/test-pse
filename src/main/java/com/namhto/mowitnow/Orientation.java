package com.namhto.mowitnow;

import java.util.Arrays;

import static com.namhto.mowitnow.ErrorCode.CONFIGURATION_INVALID_ORIENTATION;

public enum Orientation {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private String value;

    Orientation(String value) {
        this.value = value;
    }

    public static Orientation get(String value) {
        return Arrays.stream(values())
                .filter(orientation -> orientation.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new MowItNowException(CONFIGURATION_INVALID_ORIENTATION.withData(value)));
    }

    @Override
    public String toString() {
        return value;
    }

    public Orientation plus90Degrees() {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return this;
        }
    }

    public Orientation minus90Degrees() {
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                return this;
        }
    }
}
