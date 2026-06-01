package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.error_handling.Error_output_to_user;

public class Writing_to_the_drawio_file {

    Error_output_to_user eotu = Error_output_to_user.getInstance();

    private static final Logger logger = LogManager.getLogger(Writing_to_the_drawio_file.class);

    public void record (String file_path_and_or_name, String the_billing_table){
        //читаем с каким файлом и работать вставляем таблицу
        try {
            logger.info("чтение файла .drawio");
            String sheet = "<mxCell id=\"1\" parent=\"0\"";
            String empty_sheet = sheet+"/>";
            String recorded_sheet = sheet + " " +"/>";
                    //indexOf
            String where_to_record = "";
            Path path = Paths.get(file_path_and_or_name);//name.drawio
            if (Files.exists(path)){
                String content = new String( Files.readAllBytes(path) );
                if (content.indexOf(empty_sheet) != -1){
                    where_to_record = empty_sheet;}
                else {
                    where_to_record = recorded_sheet;}

                content = content.replaceAll(where_to_record, where_to_record + the_billing_table);

                //запись обратно
                FileWriter writer = new FileWriter(file_path_and_or_name, false);
                writer.write(content);
                writer.flush();
            }
            else {
                throw new IOException();
            }

        } catch (IOException e) {
            eotu.entering_error("чтение файла привело к ошибке по пути " + file_path_and_or_name);
            logger.fatal("чтение файла привело к ошибке по пути " + file_path_and_or_name);
            //e.printStackTrace();
        }
    }
}
