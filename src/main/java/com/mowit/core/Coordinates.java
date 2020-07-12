package com.mowit.core;

public class Coordinates {

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
}
