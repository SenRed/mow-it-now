package com.mowit.core;

import com.mowit.core.exception.InvalidMowerStartingPosition;

public class Mower {

    Lawn lawn;
    Position position;

    public void createStartingPosition(String position) throws InvalidMowerStartingPosition {
        String[] inputPosition = position.split(" ");
        if (inputPosition.length != 3)
            throw new InvalidMowerStartingPosition(position);

        String startingCoordinates = String.join( " ", inputPosition[0], inputPosition[1]);
        if (Coordinates.isInvalidCoordinates(startingCoordinates))
            throw new InvalidMowerStartingPosition(position);

        Coordinates coordinates = new Coordinates(startingCoordinates);

        if (lawn.isOutLawn(coordinates)) {
            throw new InvalidMowerStartingPosition(position);
        }

        Direction direction = Direction.getDirectionFromString(inputPosition[2]);

        this.position = new Position(coordinates,direction);
    }

    public Lawn getLawn() {
        return lawn;
    }

    public void setLawn(Lawn lawn) {
        this.lawn = lawn;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

}
