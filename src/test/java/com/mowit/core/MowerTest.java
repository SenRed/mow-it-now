package com.mowit.core;

import com.mowit.core.exception.InvalidCoordinates;
import com.mowit.core.exception.InvalidMowerStartingPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowerTest {
    Lawn lawn;
    Mower mower;

    @BeforeEach
    void init() throws InvalidCoordinates {
        lawn = new Lawn();
        lawn.createLimitLawnCoordinates("5 5");
        mower = new Mower();
        mower.setLawn(lawn);
    }

    @Test
    void should_throw_invalid_starting_position() {
        //Given
        String invalidPosition = "A 10 9";
        String positionOutOfLawn = "100 10 N";

        //When//Then
        assertThatThrownBy(() -> mower.createStartingPosition(invalidPosition))
                .isInstanceOf(InvalidMowerStartingPosition.class);
        assertThatThrownBy(() -> mower.createStartingPosition(positionOutOfLawn))
                .isInstanceOf(InvalidMowerStartingPosition.class);
    }

    @Test
    void should_create_mower_with_starting_position() throws InvalidMowerStartingPosition {
        //Given
        Position exceptedPosition = new Position(new Coordinates(0, 0), Direction.NORTH);
        //When
        mower.createStartingPosition("0 0 N");
        //Then
        assertThat(mower.getPosition()).isEqualTo(exceptedPosition);
    }

}