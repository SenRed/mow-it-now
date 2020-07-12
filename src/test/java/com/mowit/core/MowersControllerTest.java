package com.mowit.core;

import com.mowit.core.exception.InvalidCommand;
import org.junit.jupiter.api.Test;
import util.FileLoader;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowersControllerTest {

    @Test
    void should_throw_invalid_file_instruction(){
        File invalidInstructions = new File(FileLoader.getFile("invalid_instructions.txt"));
        assertThatThrownBy(()->new MowersController().processFile(invalidInstructions))
                .isInstanceOf(InvalidCommand.class);
    }
    //TODO: should_process_valid_file
    //TODO: should start mowing when rover are initiated

}