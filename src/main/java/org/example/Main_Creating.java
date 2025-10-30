package org.example;

public class Main_Creating {
    public void work(String name_of_the_draw_fil, String text_tabel, int style, int height, int width, int x, int y){
        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();
        c_tabulalr_data.reading_from_text(text_tabel);
        var text = c_table.create(c_tabulalr_data.getHeight_width(), style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        writing.record(name_of_the_draw_fil, text);
    }

    public void work(String name_of_the_draw_fil, String text_tabel, int style){
        int x = 0; int y = 0;
        int height = 40; int width = 60;

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();

        c_tabulalr_data.reading_from_text(text_tabel);
        var text = c_table.create(c_tabulalr_data.getHeight_width(), style, x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        writing.record(name_of_the_draw_fil, text);
    }
    //// excel
    public void work(String name_of_the_draw_fil, int style, int height, int width, int x, int y, String name_of_the_excel_fil,  String name_sheet, int columnIndex_star, int columnIndex_end, int startRow, int endRow){

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();

        c_tabulalr_data.reading_from_excel(name_of_the_excel_fil, name_sheet, columnIndex_star, columnIndex_end, startRow,endRow);
        var text = c_table.create(c_tabulalr_data.getHeight_width() , style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        writing.record(name_of_the_draw_fil, text);
    }
    public void work(String name_of_the_draw_fil, String name_of_the_excel_fil, int style, String name_sheet, int columnIndex_star, int columnIndex_end, int startRow, int endRow){
        int x = 0; int y = 0;
        int height = 40; int width = 60;

        var c_table = new Creating_Table();
        var writing = new Writing_to_the_drawio_file();
        var c_tabulalr_data = new Converting_Tabular_Data();

        c_tabulalr_data.reading_from_excel(name_of_the_excel_fil, name_sheet, columnIndex_star, columnIndex_end, startRow,endRow);
        var text = c_table.create(c_tabulalr_data.getHeight_width(), style,  x,y  ,c_tabulalr_data.getNumber_height() ,c_tabulalr_data.getNumber_width() ,  height,width);
        writing.record(name_of_the_draw_fil, text);
    }
}
