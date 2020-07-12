package features;

import com.mowit.core.MowIt;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class MowItFeaturesTest {

    @Test
    void it_should_return_when_no_obstacle_found() {
        //Given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String instructionsFile = this.getClass().getClassLoader().getResource("instructions.txt").getPath();
        String[] mowItArguments = {instructionsFile};
        //When
        MowIt.main(mowItArguments);
        //Then
        assertThat(outputStream).isEqualTo("1 3 N\n5 1 E");
    }
}
