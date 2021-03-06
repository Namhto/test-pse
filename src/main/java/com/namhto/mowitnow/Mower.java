package com.namhto.mowitnow;

import static com.namhto.mowitnow.ErrorCode.POSITION_OUTSIDE_LIMITS;

public class Mower {

    private Position position;

    private Position northEastLimitPosition;

    private Orientation orientation;

    private Position southWestLimit = new Position(0, 0);

    public Mower(Position position, Orientation orientation, Position northEastLimitPosition) {
        if (orientation == null) {
            throw new IllegalArgumentException("Initial orientation must be provided");
        }
        if (northEastLimitPosition == null) {
            throw new IllegalArgumentException("North-east limit position must be provided");
        }
        if (position.isGreaterThan(northEastLimitPosition)) {
            throw new MowItNowException(POSITION_OUTSIDE_LIMITS.withData(position));
        }
        this.position = position;
        this.orientation = orientation;
        this.northEastLimitPosition = northEastLimitPosition;
    }

    public String getPositionAndOrientation() {
        return String.format("%d %d %s", position.x, position.y, orientation);
    };

    public Mower execute(Instruction instruction) {
        switch (instruction) {
            case MOVE_FORWARD:
                moveForward();
                return this;
            case TURN_RIGHT:
                turnRight();
                return this;
            case TURN_LEFT:
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
        Position nextPosition = new Position(position);
        switch (orientation) {
            case NORTH:
                nextPosition.y += 1;
                break;
            case SOUTH:
                nextPosition.y -=1;
                break;
            case EAST:
                nextPosition.x += 1;
                break;
            case WEST:
                nextPosition.x -= 1;
                break;
        }
        if (isInTerrainLimits(nextPosition)) {
            position = nextPosition;
        }
    }

    private boolean isInTerrainLimits(Position position) {
        return (position.isGreaterThan(southWestLimit) || position.equals(southWestLimit)) &&
                (northEastLimitPosition.isGreaterThan(position) || northEastLimitPosition.equals(position));
    }
}
