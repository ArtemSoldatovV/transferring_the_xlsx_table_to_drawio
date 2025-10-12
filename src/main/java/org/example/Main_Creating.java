package org.example;

public class Main_Creating {
    public void work(String file_path_and_or_name, int height, int width, int y, int x){
        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();

        c_tabulalr_data.reading_from_text(file_path_and_or_name);
        var text = c_table.create(c_tabulalr_data.height_width,  y,x  ,c_tabulalr_data.number_height ,c_tabulalr_data.number_width ,  height,width);
        writing.record(file_path_and_or_name, text);
    }

    public void work(String file_path_and_or_name){
        int x = 0; int y = 0;
        int height = 40; int width = 60;

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();

        c_tabulalr_data.reading_from_text(file_path_and_or_name);
        var text = c_table.create(c_tabulalr_data.height_width,  y,x  ,c_tabulalr_data.number_height ,c_tabulalr_data.number_width ,  height,width);
        writing.record(file_path_and_or_name, text);
    }
}
