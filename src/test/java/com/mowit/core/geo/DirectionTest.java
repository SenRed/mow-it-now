package com.mowit.core.geo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void north_should_be_between_west_and_east(){
        //Given
        Direction direction = Direction.NORTH;
        //Then
        assertThat(direction.getLeft()).isEqualTo(Direction.WEST);
        assertThat(direction.getRight()).isEqualTo(Direction.EAST);
    }
    @Test
    void east_should_be_between_north_and_south(){
        //Given
        Direction direction = Direction.EAST;
        //Then
        assertThat(direction.getLeft()).isEqualTo(Direction.NORTH);
        assertThat(direction.getRight()).isEqualTo(Direction.SOUTH);
    }
    @Test
    void south_should_be_between_east_and_west(){
        //Given
        Direction direction = Direction.SOUTH;
        //Then
        assertThat(direction.getLeft()).isEqualTo(Direction.EAST);
        assertThat(direction.getRight()).isEqualTo(Direction.WEST);
    }
    @Test
    void west_should_be_between_north_and_south(){
        //Given
        Direction direction = Direction.WEST;
        //Then
        assertThat(direction.getLeft()).isEqualTo(Direction.SOUTH);
        assertThat(direction.getRight()).isEqualTo(Direction.NORTH);
    }
}