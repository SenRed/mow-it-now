package features;

import com.mowit.core.MowIt;
import com.mowit.core.exception.InvalidCommand;
import com.mowit.core.exception.InvalidFilePath;
import org.junit.jupiter.api.Test;
import util.FileLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class MowItFeaturesTest {

    @Test
    void it_should_return_when_no_obstacle_found() throws InvalidFilePath, IOException, InvalidCommand, ExecutionException, InterruptedException {
        //Given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String instructionsFile = FileLoader.getFile("instructions.txt");
        String[] mowItArguments = {instructionsFile};
        //When
        MowIt.main(mowItArguments);
        //Then
        assertThat(outputStream.toString()).isEqualTo("1 3 N\r\n5 1 E\r\n");
    }
}
