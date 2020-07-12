package com.mowit.core.geo;

import java.util.Objects;

public class Position {

    private Coordinates coordinates;
    private Direction direction;

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

    public void goRight() {
        this.direction= this.direction.getRight();
    }

    public void goLeft() {
        this.direction= this.direction.getLeft();
    }



    public Coordinates getNextCoordinates() {
        Coordinates nextCoordinates = new Coordinates(coordinates);
        if (this.direction == Direction.EAST) {
            nextCoordinates.incrementXAxis(1);
        }
        if (this.direction == Direction.WEST) {
            nextCoordinates.incrementXAxis(-1);
        }
        if (this.direction == Direction.NORTH) {
            nextCoordinates.incrementYAxis(1);
        }
        if (this.direction == Direction.SOUTH) {
            nextCoordinates.incrementYAxis(-1 );
        }
        return nextCoordinates;
    }

    public void setCoordinates(Coordinates nextCoordinates) {
        this.coordinates = nextCoordinates;
    }
}
