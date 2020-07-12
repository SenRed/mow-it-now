package com.mowit.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordinates  {

    private int x;
    private int y;


    public Coordinates(String dimensions) {
        String[] dimensionsCardinals = dimensions.split(" ");
        this.x = Integer.valueOf(dimensionsCardinals[0]);
        this.y = Integer.valueOf(dimensionsCardinals[1]);
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static boolean isInvalidCoordinates(String coordinates){
        String pattern = "(^\\d+\\s\\d+$)";
        Pattern lawnPattern = Pattern.compile(pattern);
        Matcher matcher = lawnPattern.matcher(coordinates);
        return !matcher.find();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
