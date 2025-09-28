package org.example;

import java.util.ArrayList;

public class Converting_Tabular_Data {

    ArrayList<String> height_width;
    int number_height;
    int number_width;
    public Converting_Tabular_Data(ArrayList<String> height_width, int number_height, int number_width){
    }
    //table_is_in_text
    //String table_text = "";

    //\n
    //.\t|.\n
    //(.)\t|(.*)

    public static Converting_Tabular_Data reading_from_text(String table_text){
        String[] height_height = table_text.split("\n");
        int number_height = height_height.length;
        ArrayList<String> height_width = new ArrayList<String>();
        //-1 потому, что может быть таблица с обеденными ячейками для названия таблицы
        int number_width = height_height[-1].split("\t").length;

        for (String i : height_height){

            for (String q : i.split("\t")) {
                height_width.add( q );
            }

        }
        return new Converting_Tabular_Data(height_width, number_height, number_width);
    }

    public static void /*Converting_Tabular_Data*/ reading_from_excel(String name_of_the_excel_file){
        Workbook wb = new Workbook(name_of_the_excel_file);


        return ;
    }

    public ArrayList<String> getHeight_width() {return height_width;}
    public void setHeight_width(ArrayList<String> height_width) {this.height_width = height_width;}

    public int getNumber_height() {return number_height;}
    public void setNumber_height(int number_height) {this.number_height = number_height;}

    public int getNumber_width() {return number_width;}
    public void setNumber_width(int number_width) {this.number_width = number_width;}
}
