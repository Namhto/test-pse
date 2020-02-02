package com.namhto.mowitnow;

import java.util.Objects;

public class Position {

    public int x;

    public int y;

    public Position(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(String.format("Position is invalid (x: %d, y: %d)", x, y));
        }
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position to copy must not be null");
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

    public boolean isGreaterThan(Position position) {
        return x > position.x || y > position.y;
    }
}
