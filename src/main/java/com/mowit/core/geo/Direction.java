package com.mowit.core.geo;

import com.mowit.core.exception.InvalidMowerStartingPosition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Direction {
    NORTH("N"),
    SOUTH("S"),
    WEST("W"),
    EAST("E");

    private String code;
    private Direction left;
    private Direction right;

    private static final Map<String, Direction> lookup;

    static {
        Map<String, Direction> map = new HashMap<>();

        NORTH.initClosedDirection(WEST, EAST);
        SOUTH.initClosedDirection(EAST, WEST);
        WEST.initClosedDirection(SOUTH, NORTH);
        EAST.initClosedDirection(NORTH, SOUTH);

        for (Direction dir : Direction.values()) {
            map.put(dir.code, dir);
        }
        lookup = Collections.unmodifiableMap(map);
    }

    Direction(final String code) {
        this.code = code;
    }

    private void initClosedDirection(Direction left, Direction right) {
        this.left = left;
        this.right = right;
    }

    public static Direction getDirectionFromString(String direction) throws InvalidMowerStartingPosition {
        return Optional.of(lookup.get(direction))
                .orElseThrow(() -> new InvalidMowerStartingPosition(direction));
    }

    public String getCode() {
        return code;
    }

    public Direction getLeft() {
        return this.left;
    }

    public Direction getRight() {
        return right;
    }
}
