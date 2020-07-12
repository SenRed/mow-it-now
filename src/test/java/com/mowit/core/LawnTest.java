package com.mowit.core;

import com.mowit.core.exception.LawnLimitParsingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LawnTest {

    @Test
    void should_throw_invalid_dimension() {
        assertThatThrownBy(()->new Lawn().createLimitLawnCoordinates("A B"))
                .isInstanceOf(LawnLimitParsingException.class);
    }
    @Test void should_create_dimensions_from_valid_string() throws LawnLimitParsingException {
        //Given
        Lawn lawn = new Lawn();
        Coordinates expectedCoordinates = new Coordinates(20,20);
        //When
        lawn.createLimitLawnCoordinates("20 20");
        //Then
        assertThat(lawn.getLimitCoordinates()).isEqualToComparingFieldByField(expectedCoordinates);
    }
}