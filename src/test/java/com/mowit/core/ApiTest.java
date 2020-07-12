package com.mowit.core;

import com.mowit.core.exception.InvalidFilePath;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApiTest {
    @Test
    void should_throw_invalid_file_instructions() {
        assertThatThrownBy(() -> MowIt.main(new String[]{"invalid-file"}))
                .isInstanceOf(InvalidFilePath.class);
    }

    @Test
    void should_run_if_valid_file_input() {
        String validInstructionFile =this.getClass().getClassLoader().getResource("instructions.txt").getPath();
        assertThatThrownBy(() -> MowIt.main(new String[]{validInstructionFile})).doesNotThrowAnyException();
    }

}
