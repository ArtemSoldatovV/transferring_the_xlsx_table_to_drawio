package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.data_integrity_control.Сhecking_integrity_tables;
import org.example.excel_positions.Translating_Excel_Position;

public class Main_Creating {
    private static final Logger logger = LogManager.getLogger(Main_Creating.class);
    public void work(String name_of_the_draw_fil, String text_tabel, int style, int height, int width, int x, int y){
        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();
        Сhecking_integrity_tables c_i_t = new Сhecking_integrity_tables();
        c_tabulalr_data.reading_from_text(text_tabel);
        var text = c_table.create(c_tabulalr_data.getHeight_width(), style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        if (c_i_t.chek_tabel(text)){
            writing.record(name_of_the_draw_fil, text);
            logger.info("таблица загружена в файл");
        }else {
            logger.error("Ошибка: таблица построена не верно");
        }
    }

    public void work(String name_of_the_draw_fil, String text_tabel, int style){
        int x = 0; int y = 0;
        int height = 40; int width = 60;

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();
        Сhecking_integrity_tables c_i_t = new Сhecking_integrity_tables();

        c_tabulalr_data.reading_from_text(text_tabel);
        var text = c_table.create(c_tabulalr_data.getHeight_width(), style, x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        if (c_i_t.chek_tabel(text)){
            writing.record(name_of_the_draw_fil, text);
            logger.info("таблица загружена в файл");
        }else {
            logger.error("Ошибка: таблица построена не верно");
        }
    }
    //// excel
    public void work(String name_of_the_draw_fil, int style, int height, int width, int x, int y, String name_of_the_excel_fil,  String name_sheet, int columnIndex_star, int columnIndex_end, int startRow, int endRow){

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();
        Сhecking_integrity_tables c_i_t = new Сhecking_integrity_tables();
        c_tabulalr_data.reading_from_excel(name_of_the_excel_fil, name_sheet, columnIndex_star, columnIndex_end, startRow,endRow);

        var text = c_table.create(c_tabulalr_data.getHeight_width() , style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        if (c_i_t.chek_tabel(text)){
            writing.record(name_of_the_draw_fil, text);
            logger.info("таблица загружена в файл");
        }else {
            logger.error("Ошибка: таблица построена не верно");
        }
    }
    public void work(String name_of_the_draw_fil, String name_of_the_excel_fil, int style, String name_sheet, int columnIndex_star, int columnIndex_end, int startRow, int endRow){
        int x = 0; int y = 0;
        int height = 40; int width = 60;

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();
        Сhecking_integrity_tables c_i_t = new Сhecking_integrity_tables();

        c_tabulalr_data.reading_from_excel(name_of_the_excel_fil, name_sheet, columnIndex_star, columnIndex_end, startRow,endRow);
        var text = c_table.create(c_tabulalr_data.getHeight_width(), style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        if (c_i_t.chek_tabel(text)){
            writing.record(name_of_the_draw_fil, text);
            logger.info("таблица загружена в файл");
        }else {
            logger.error("Ошибка: таблица построена не верно");
        }
    }

    public void work(String name_of_the_draw_fil, int style, int height, int width, int x, int y, String name_of_the_excel_fil,  String name_sheet, String String_coordinates_of_the_selected_table){

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();
        Сhecking_integrity_tables c_i_t = new Сhecking_integrity_tables();
        Translating_Excel_Position t_e_p = new Translating_Excel_Position();

        int[] position = t_e_p.converting_position_excel_to_number(String_coordinates_of_the_selected_table);
        c_tabulalr_data.reading_from_excel(name_of_the_excel_fil, name_sheet, position[0], position[2], position[1],position[3]);

        var text = c_table.create(c_tabulalr_data.getHeight_width() , style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        if (c_i_t.chek_tabel(text)){
            writing.record(name_of_the_draw_fil, text);
            logger.info("таблица загружена в файл");
        }else {
            logger.error("Ошибка: таблица построена не верно");
        }
    }
    public void work(String name_of_the_draw_fil, String name_of_the_excel_fil, int style, String name_sheet, String String_coordinates_of_the_selected_table){
        int x = 0; int y = 0;
        int height = 40; int width = 60;

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();
        Сhecking_integrity_tables c_i_t = new Сhecking_integrity_tables();

        Translating_Excel_Position t_e_p = new Translating_Excel_Position();
        int[] position = t_e_p.converting_position_excel_to_number(String_coordinates_of_the_selected_table);

        c_tabulalr_data.reading_from_excel(name_of_the_excel_fil, name_sheet, position[0], position[2], position[1],position[3]);
        var text = c_table.create(c_tabulalr_data.getHeight_width(), style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        if (c_i_t.chek_tabel(text)){
            writing.record(name_of_the_draw_fil, text);
            logger.info("таблица загружена в файл");
        }else {
            logger.error("Ошибка: таблица построена не верно");
        }
    }
}