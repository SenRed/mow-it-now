package com.mowit.core;

import com.mowit.core.exception.InvalidCoordinates;
import com.mowit.core.geo.Coordinates;

class Lawn {

    Coordinates limitCoordinates;

    public void createLimitLawnCoordinates(String dimensions) throws InvalidCoordinates {
        if (Coordinates.isInvalidCoordinates(dimensions))
            throw new InvalidCoordinates(dimensions);
        limitCoordinates = new Coordinates(dimensions);
    }

    public Coordinates getLimitCoordinates() {
        return this.limitCoordinates;
    }

    public boolean isOutLawn(Coordinates coordinates) {
        return coordinates.getX() < 0 ||
                coordinates.getX() > limitCoordinates.getX()
                || coordinates.getY() < 0
                || coordinates.getY() > limitCoordinates.getY();
    }
}
