package com.namhto.mowitnow;

public class Mower {

    private int x;

    private int y;

    private Orientation orientation;

    public Mower(int x, int y, Orientation orientation) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(String.format("Initial position is invalid (x: %d, y: %d)", x, y));
        }
        if (orientation == null) {
            throw new IllegalArgumentException("Initial orientation must be provided");
        }
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public String getPositionAndOrientation() {
        return String.format("%d %d %s", x, y, orientation);
    };

    public Mower move(Instruction instruction) {
        switch (instruction) {
            case A:
                moveForward();
                return this;
            case D:
                turnRight();
                return this;
            case G:
                turnLeft();
                return this;
            default:
                return this;
        }
    }

    private void turnLeft() {
        orientation = orientation.minus90Degrees();
    }

    private void turnRight() {
        orientation = orientation.plus90Degrees();
    }

    private void moveForward() {
        // TODO: Add upper right corner coordinates for check here
    }
}
