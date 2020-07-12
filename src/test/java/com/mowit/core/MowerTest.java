package com.mowit.core;

import com.mowit.core.command.Command;
import com.mowit.core.command.CommandFactory;
import com.mowit.core.exception.InvalidCoordinates;
import com.mowit.core.exception.InvalidMovingCommand;
import com.mowit.core.exception.InvalidMowerStartingPosition;
import com.mowit.core.geo.Coordinates;
import com.mowit.core.geo.Direction;
import com.mowit.core.geo.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowerTest {
    Lawn lawn;
    Mower mower;

    @BeforeEach
    void init() throws InvalidCoordinates {
        lawn = new Lawn();
        lawn.createLimitLawnCoordinates("5 5");
        mower = new Mower(lawn);
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
}