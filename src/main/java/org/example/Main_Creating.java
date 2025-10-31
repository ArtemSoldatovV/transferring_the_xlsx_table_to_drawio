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
//{"path_draw":"C:\\Users\\Александр\\Documents\\Важно\\дипломная работа\\test.drawio","table_in_the_text":"1\ta\t7 2\tb\t8 3\tc\t9 4\td\t10 5\te\t11 6\tf\t12","height":40,"width":60,"x":0,"y":0,"style":1}
//        2025-10-31 15:39:36,602 [INFO] org.example.Converting_Tabular_Data - создание таблицы из текста
//[1, a, 7, 2, b, 8, 3, c, 9, 4, d, 10, 5, e, 11, 6, f, 12]
//        6
//        3
//        {"path_draw":"C:\\Users\\Александр\\Documents\\Важно\\дипломная работа\\test.drawio","path_excel":"C:\\Users\\Александр\\Documents\\Важно\\дипломная работа\\test.xlsx","style":1,"excel_sheet":"Sheet1","columnIndex_start":0,"columnIndex_end":3,"startRow":0,"endRow":6,"height":40,"width":60,"x":0,"y":0}
//        2025-10-31 15:39:39,706 [INFO] org.example.Converting_Tabular_Data - создание таблицы из файла
//########+
//        [1, a, 7, 2, b, 8, 3, c, 9, 4, d, 10, 5, e, 11, 6, f, 12]
//        4
//        7