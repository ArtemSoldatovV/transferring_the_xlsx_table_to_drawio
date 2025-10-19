package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Writing_to_the_drawio_file {
    public void record (String file_path_and_or_name, String the_billing_table){
        //читаем с каким файлом и работать вставляем таблицу
        try {
            String where_to_record = "<mxCell id=\"1\" parent=\"0\" />";
            Path path = Paths.get(file_path_and_or_name);//name.drawio
            String content = new String( Files.readAllBytes(path) );

            content = content.replaceAll(where_to_record, where_to_record + the_billing_table);

            //запись обратно
            FileWriter writer = new FileWriter(file_path_and_or_name, false);
            writer.write(content);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
