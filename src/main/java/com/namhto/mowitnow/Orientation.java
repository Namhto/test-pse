package com.namhto.mowitnow;

public enum Orientation {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private String value;

    Orientation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
