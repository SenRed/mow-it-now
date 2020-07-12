package com.mowit.core;

import com.mowit.core.exception.InvalidCommand;
import org.junit.jupiter.api.Test;
import util.FileLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowersControllerTest {

    @Test
    void should_throw_invalid_file_instruction(){
        File invalidInstructions = new File(FileLoader.getFile("invalid_instructions.txt"));
        assertThatThrownBy(()->new MowersController().processFile(invalidInstructions))
                .isInstanceOf(InvalidCommand.class);
    }
    @Test void should_process_valid_file() throws IOException, InvalidCommand {
        //Given
        File validFile = new File(FileLoader.getFile("simple_instructions.txt"));
        Lawn lawn = new Lawn();
        lawn.createLimitLawnCoordinates("5 5");
        Mower mower = new Mower(lawn);
        mower.createStartingPosition("0 0 N");
        mower.createCommands("ADG");
        List<Mower> expectedMowers= List.of(mower,mower,mower);
        MowersController mowersController = new MowersController();
        //When
        mowersController.processFile(validFile);
        //Then
        assertThat(mowersController.getMowers()).containsAll(expectedMowers);
    }
    //TODO: should start mowing when rover are initiated

}