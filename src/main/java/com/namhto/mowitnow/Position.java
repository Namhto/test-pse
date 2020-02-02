package com.namhto.mowitnow;

import java.util.Objects;

import static com.namhto.mowitnow.ErrorCode.INVALID_POSITION;

public class Position {

    public int x;

    public int y;

    public Position(int x, int y) {
        if (x < 0 || y < 0) {
            throw new MowItNowException(INVALID_POSITION.withData(String.format("x: %d, y: %d", x, y)));
        }
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        if (position == null) {
            throw new MowItNowException(INVALID_POSITION.withData("position to copy is null"));
        }
        x = position.x;
        y = position.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean isGreaterThan(Position position) {
        return x > position.x || y > position.y;
    }
}
