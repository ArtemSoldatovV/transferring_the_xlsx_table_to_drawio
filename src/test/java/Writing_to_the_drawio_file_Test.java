import org.example.Writing_to_the_drawio_file;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Writing_to_the_drawio_file_Test {
    Writing_to_the_drawio_file wttdf = new Writing_to_the_drawio_file();
    @Test
    public void record(){
        try {
            String path_to_the_program = System.getProperty("user.dir");
            String path_to_the_test = "src\\test\\files";
            String path_to_test_draw = path_to_the_program + "\\" + path_to_the_test + "\\" + "test.drawio";

            FileWriter writer = new FileWriter(path_to_test_draw, false);
            writer.flush();

            wttdf.record(path_to_the_program + "\\" + path_to_the_test + "\\" + "test.drawio", "test");
            assertEquals(true, true);
        } catch (IOException e) {
            assertEquals(true, false);
            e.printStackTrace();
        }
    }

}
