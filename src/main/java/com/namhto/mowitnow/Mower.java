package com.namhto.mowitnow;

public class Mower {

    private Position position;

    private Position northEastLimitPosition;

    private Orientation orientation;

    public Mower(Position position, Orientation orientation, Position northEastLimitPosition) {
        if (orientation == null) {
            throw new IllegalArgumentException("Initial orientation must be provided");
        }
        if (northEastLimitPosition == null) {
            throw new IllegalArgumentException("North-east limit position must be provided");
        }
        if (position.isGreaterThan(northEastLimitPosition)) {
            throw new IllegalArgumentException("Position can not be outside of the terrain limits");
        }
        this.position = position;
        this.orientation = orientation;
        this.northEastLimitPosition = northEastLimitPosition;
    }

    public String getPositionAndOrientation() {
        return String.format("%d %d %s", position.x, position.y, orientation);
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
