package com.mowit.core;

import com.mowit.core.exception.InvalidCoordinates;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LawnTest {

    @Test
    void should_throw_invalid_dimension() {
        assertThatThrownBy(() -> new Lawn().createLimitLawnCoordinates("A B"))
                .isInstanceOf(InvalidCoordinates.class);
    }

    @Test
    void should_create_dimensions_from_valid_string() throws InvalidCoordinates {
        //Given
        Lawn lawn = new Lawn();
        Coordinates expectedCoordinates = new Coordinates(20, 20);
        //When
        lawn.createLimitLawnCoordinates("20 20");
        //Then
        assertThat(lawn.getLimitCoordinates()).isEqualToComparingFieldByField(expectedCoordinates);
    }

    @Test
    void should_return_check_coordinates_is_in_lawn() throws InvalidCoordinates {
        //Given
        Lawn lawn = new Lawn();
        lawn.createLimitLawnCoordinates("20 20");
        Coordinates outLawnCoordinates = new Coordinates(5, 50);
        Coordinates inLawnCoordinates = new Coordinates(20, 20);
        //When//Then
        assertThat(lawn.isOutLawn(outLawnCoordinates)).isTrue();
        assertThat(lawn.isOutLawn(inLawnCoordinates)).isFalse();
    }
}