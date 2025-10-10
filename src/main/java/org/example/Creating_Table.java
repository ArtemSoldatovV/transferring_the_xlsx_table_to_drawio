package org.example;

import java.util.ArrayList;

import static org.example.Creating_Random_Id.generation_id;

public class Creating_Table {
//    int height = 40;
//    int width = 60;
//    int y = 190 ;
//    int x = 230 ;
//    int cells_in_height = 3;
//    int cells_in_width = 3;
//    String cell_value = "w";
//    int item_number = 1;
//    String id = generation_id();


    public static String create(ArrayList<String> the_value_of_all_cells , int y, int x, int cells_in_height, int cells_in_width, int height, int width)
    {
        String id = generation_id();
        int item_number = 1;//номер элемента
        int total_number_of_cells = cells_in_height * cells_in_width;// скольк должно быть ячеек
        int how_many_cells_are_made = 0;// число ячеек
        int number_of_width = 0;// число строк
        String exit = table_root( id,  item_number,  y,  x,  cells_in_height,  cells_in_width,  height,  width);
        while (how_many_cells_are_made < total_number_of_cells){
            item_number += 1;

            if(number_of_width == 0 |
                    how_many_cells_are_made % cells_in_width == 0 & how_many_cells_are_made / cells_in_width == number_of_width){//тут нужна логика для создания строк
                number_of_width += 1;
                exit += table_string( id, item_number,  cells_in_height,  height,  width);
            }
            else {
                exit += table_cell( id,  item_number,  the_value_of_all_cells.get(how_many_cells_are_made),  height,  width);
                how_many_cells_are_made += 1;
            }

        }
        return exit;
    }

    private static String table_root(String id, int item_number, int y, int x, int cells_in_height, int cells_in_width, int height, int width){
        //строка
        String basics_of_the_table = "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=table;startSize=0;container=1;collapsible=0;childLayout=tableLayout;fontSize=16;\" parent=\"1\" vertex=\"1\">\\n<mxGeometry x=\"" + x + "\" y=\"" + y + "\" width=\"" + width * cells_in_height + "\" height=\"" + height * cells_in_width + "\" as=\"geometry\" />\\n</mxCell>\"";
        return basics_of_the_table;
    }
    private static String table_string(String id, int item_number, int cells_in_height, int height, int width){
        //строка
        String basis_of_the_string = "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontSize=16;\" parent=\"-r2dthNOID2k0gEMgki2-1\" vertex=\"1\">\\n<mxGeometry width=\"" + width * cells_in_height + "\" height=\"" + height + "\" as=\"geometry\" />\\n</mxCell>";
        return basis_of_the_string;
    }
    private static String table_cell(String id, int item_number, String cell_value, int height, int width){
        //ячейка
        String base_of_the_cell = "<mxCell id=\"" + id + "-" + item_number + "\" value=\"(" + cell_value + ")\" style=\"shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;fontSize=16;\" parent=\"-r2dthNOID2k0gEMgki2-2\" vertex=\"1\">\\n<mxGeometry width=\"" + width +"\" height=\"" + height +"\" as=\"geometry\">\\n<mxRectangle width=\"" + width +"\" height=\"" + height +"\" as=\"alternateBounds\" />\\n</mxGeometry>\\n</mxCell>";
        return base_of_the_cell;
    }

}