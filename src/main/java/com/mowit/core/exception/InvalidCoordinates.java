package com.mowit.core.exception;

public class InvalidCoordinates extends InvalidCommand{
    public InvalidCoordinates(String dimensions) {
        super("cause "+dimensions);
    }
}
