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
            Path path = Paths.get(file_path_and_or_name);//name.drawio
            String content = new String( Files.readAllBytes(path) );
            //System.out.println(content);
            //String content_2 = new String(Files.readAllBytes(Paths.get(file_path_and_or_name)));

            content = content.replaceAll("<root>", "<root>" + the_billing_table);

            //запись обратно
            FileWriter writer = new FileWriter(file_path_and_or_name, false);
            writer.write(content);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
