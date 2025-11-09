import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Overwriting_the_test_file {
    private static final Logger logger = LogManager.getLogger(Overwriting_the_test_file.class);
    public void overwriting(){
        try {
            String where_to_record = "<mxCell id=\"1\" parent=\"0\" />";

            String path_to_the_program = System.getProperty("user.dir");
            String path_to_the_test = "src\\test\\files";
            String path_to_test_draw = (path_to_the_program + "\\" + path_to_the_test + "\\" + "test.drawio").replace("\\", "\\\\");

            String template_for_an_empty_draw_file = "";

            Path path = Paths.get(path_to_test_draw);//name.drawio
            String content = new String( Files.readAllBytes(path) );

            content = content.replaceAll(where_to_record, template_for_an_empty_draw_file);

            //запись обратно
            FileWriter writer = new FileWriter(path_to_test_draw, false);
            writer.write(content);
            writer.flush();

        } catch (IOException e) {
            logger.fatal("чтение файла привело к ошибке по пути ");
            e.printStackTrace();
        }
    }
}
