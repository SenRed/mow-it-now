package com.mowit.core;

import com.mowit.core.command.Command;
import com.mowit.core.command.CommandFactory;
import com.mowit.core.exception.InvalidCoordinates;
import com.mowit.core.exception.InvalidMovingCommand;
import com.mowit.core.exception.InvalidMowerStartingPosition;
import com.mowit.core.geo.Coordinates;
import com.mowit.core.geo.Direction;
import com.mowit.core.geo.Obstacles;
import com.mowit.core.geo.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowerTest {
    private Mower mower;
    private String lawnFirstPosition;

    @BeforeEach
    void init() throws InvalidCoordinates {
        Lawn lawn = new Lawn();
        lawnFirstPosition = "0 0 N";
        lawn.createLimitLawnCoordinates("5 5");
        mower = new Mower(lawn);
        Obstacles.currentMowsPosition = new ConcurrentHashMap<>();
    }

    @Test
    void should_throw_invalid_starting_position() {
        //Given
        String invalidPosition = "A 10 9";
        String positionOutOfLawn = "100 10 N";
        String validStartingPosition = "1 1 N";
        Obstacles.currentMowsPosition.put(999999999,new Coordinates(1, 1));
        //When//Then
        assertThatThrownBy(() -> mower.createStartingPosition(invalidPosition))
                .isInstanceOf(InvalidMowerStartingPosition.class);
        assertThatThrownBy(() -> mower.createStartingPosition(positionOutOfLawn))
                .isInstanceOf(InvalidMowerStartingPosition.class);
        assertThatThrownBy(() -> mower.createStartingPosition(validStartingPosition))
                .isInstanceOf(InvalidMowerStartingPosition.class);
    }

    @Test
    void should_create_mower_with_starting_position() throws InvalidMowerStartingPosition {
        //Given
        Position expectedPosition = new Position(new Coordinates(0, 0), Direction.NORTH);
        //When
        mower.createStartingPosition(lawnFirstPosition);
        //Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void should_throw_invalid_moving_commands() {
        assertThatThrownBy(() -> mower.createCommands("AB 9"))
                .isInstanceOf(InvalidMovingCommand.class);
    }

    @Test
    void should_create_valid_moving_commands() throws InvalidMovingCommand {
        //Given
        String validCommands = "ADG";
        List<Command> expectedCommands = List.of(
                CommandFactory.getCommandFromChar('A'),
                CommandFactory.getCommandFromChar('D'),
                CommandFactory.getCommandFromChar('G'));
        //When
        mower.createCommands(validCommands);
        //Then
        assertThat(mower.getCommands()).isEqualTo(expectedCommands);
    }

    @Test
    void should_turn_right() throws InvalidMowerStartingPosition {
        //Given
        mower.createStartingPosition(lawnFirstPosition);
        Position expectedPosition = new Position(new Coordinates(0, 0), Direction.EAST);
        //When
        mower.goRight();
        //Then
        assertThat(mower.getPosition()).isEqualToComparingFieldByField(expectedPosition);
    }

    @Test
    void should_turn_left() throws InvalidMowerStartingPosition {
        //Given
        mower.createStartingPosition(lawnFirstPosition);
        Position expectedPosition = new Position(new Coordinates(0, 0), Direction.WEST);
        //When
        mower.goLeft();
        //Then
        assertThat(mower.getPosition()).isEqualToComparingFieldByField(expectedPosition);
    }

    @Test
    void should_move_if_next_position_is_in_lawn() throws InvalidMowerStartingPosition {
        //Given
        mower.createStartingPosition("3 5 E");
        Position expectedPosition = new Position(new Coordinates(5, 5), Direction.SOUTH);
        //When
        mower.goForward();
        mower.goForward();
        mower.goForward();
        mower.goRight();
        //Then
        assertThat(mower.getPosition()).isEqualToComparingFieldByField(expectedPosition);
    }
    @Test void should_move_if_next_position_is_free() throws InvalidMowerStartingPosition {
        //Given
        mower.createStartingPosition("3 5 E");
        Obstacles.currentMowsPosition.put(99999999,new Coordinates(4,5));
        Position exceptedPosition = new Position(new Coordinates(3, 5), Direction.SOUTH);
        //When
        mower.goForward();
        mower.goRight();
        //Then
        assertThat(mower.getPosition()).isEqualTo(exceptedPosition);
    }
}