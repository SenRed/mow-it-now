package com.mowit.core;

import com.mowit.core.exception.LawnLimitParsingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Lawn {

    Coordinates limitCoordinates;

    public void createLimitLawnCoordinates(String dimensions) throws LawnLimitParsingException {
        String pattern = "(^\\d+\\s\\d+$)";
        Pattern lawnPattern = Pattern.compile(pattern);
        Matcher matcher = lawnPattern.matcher(dimensions);
        if (!matcher.find())
            throw new LawnLimitParsingException(dimensions);
        limitCoordinates = new Coordinates(dimensions);
    }

    public Coordinates getLimitCoordinates() {
        return this.limitCoordinates;
    }
}
