package com.mowit.core;

import java.util.Objects;

public class Position {

    private final Coordinates coordinates;
    private final Direction direction;

    public Position(Coordinates coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!Objects.equals(coordinates, position.coordinates))
            return false;
        return direction == position.direction;
    }

    @Override
    public int hashCode() {
        int result = coordinates != null ? coordinates.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
