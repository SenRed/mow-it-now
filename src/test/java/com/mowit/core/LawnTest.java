package com.mowit.core;

import com.mowit.core.exception.InvalidCoordinates;
import com.mowit.core.geo.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LawnTest {

    private Lawn lawn;
    private String lawnLimits;

    @BeforeEach
    void init(){
        lawn=new Lawn();
        lawnLimits = "20 20";
    }
    @Test
    void should_throw_invalid_dimension() {
        assertThatThrownBy(() -> lawn.createLimitLawnCoordinates("A B"))
                .isInstanceOf(InvalidCoordinates.class);
    }

    @Test
    void should_create_dimensions_from_valid_string() throws InvalidCoordinates {
        //Given
        Coordinates expectedCoordinates = new Coordinates(20, 20);
        //When
        lawn.createLimitLawnCoordinates(lawnLimits);
        //Then
        assertThat(lawn.getLimitCoordinates()).isEqualTo(expectedCoordinates);
    }

    @Test
    void should_return_check_coordinates_is_in_lawn() throws InvalidCoordinates {
        //Given
        lawn.createLimitLawnCoordinates(lawnLimits);
        Coordinates outLawnCoordinates = new Coordinates(5, 50);
        Coordinates inLawnCoordinates = new Coordinates(20, 20);
        //When//Then
        assertThat(lawn.isOutLawn(outLawnCoordinates)).isTrue();
        assertThat(lawn.isOutLawn(inLawnCoordinates)).isFalse();
    }
}