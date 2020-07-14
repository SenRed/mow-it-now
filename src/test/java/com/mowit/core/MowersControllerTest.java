package com.mowit.core;

import com.mowit.core.exception.InvalidCommand;
import com.mowit.core.geo.Coordinates;
import com.mowit.core.geo.Direction;
import com.mowit.core.geo.Obstacles;
import com.mowit.core.geo.Position;
import org.junit.jupiter.api.Test;
import util.FileLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowersControllerTest {

    @Test
    void should_throw_invalid_file_instruction() {
        File invalidInstructions = new File(FileLoader.getFile("invalid_instructions.txt"));
        assertThatThrownBy(() -> new MowersController().processFile(invalidInstructions))
                .isInstanceOf(InvalidCommand.class);
    }

    @Test
    void should_process_valid_file() throws IOException, InvalidCommand {
        //Given
        File validFile = new File(FileLoader.getFile("simple_instructions.txt"));
        Lawn lawn = new Lawn();
        lawn.createLimitLawnCoordinates("5 5");
        List<String> positions = List.of("0 0 N", "1 0 N", "2 0 N");
        List<Mower> expectedMowers = new ArrayList<>();
        for (String position : positions) {
            Mower mower = new Mower(lawn);
            mower.init(position,"ADG");
            expectedMowers.add(mower);
        }
        MowersController mowersController = new MowersController();
        //When
        mowersController.processFile(validFile);
        //Then
        assertThat(mowersController.getMowers()).containsAll(expectedMowers);
    }

    @Test
    void should_start_one_mower_on_one_time() throws InvalidCommand, ExecutionException, InterruptedException, IOException {
        //Given
        File validFile = new File(FileLoader.getFile("instructions.txt"));
        MowersController mowersController = new MowersController();
        //When
        mowersController.processFile(validFile);
        mowersController.startMowing();
        //Then
        assertThat(mowersController.getThreadPoolSize()).isEqualTo(1);
    }

    @Test
    void should_handle_when_a_mower_is_an_obstacle_to_another_mower() throws InvalidCommand, ExecutionException, InterruptedException, IOException {
        //Given
        File validFile = new File(FileLoader.getFile("obstacles_mowers.txt"));
        MowersController mowersController = new MowersController();
        List<Position> expectedPositions = Arrays.asList(new Position(new Coordinates(1, 2), Direction.WEST),
                new Position(new Coordinates(1, 3), Direction.WEST));
        List<Coordinates> expectedCoordinates = expectedPositions.stream()
                .map(Position::getCoordinates).collect(Collectors.toList());
        //When
        mowersController.processFile(validFile);
        mowersController.startMowing();
        //Then
        assertThat(Obstacles.currentMowsPosition.values())
                .containsAll(expectedCoordinates);
        assertThat(mowersController.getMowers())
                .flatExtracting(Mower::getPosition)
                .isEqualTo(expectedPositions);
    }
}