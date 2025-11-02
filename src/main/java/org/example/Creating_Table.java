package org.example;

import java.util.ArrayList;

import static org.example.Creating_Random_Id.generation_id;


public class Creating_Table {

    public static String create(ArrayList<String> the_value_of_all_cells , int style , int x, int y, int cells_in_height, int cells_in_width, int height, int width)
    {
        String id = generation_id();
        int item_number = 1;//номер элемента
        int total_number_of_cells = cells_in_height * cells_in_width;// скольк должно быть ячеек
        int how_many_cells_are_made = 0;// число ячеек
        int account_cell = 0;//какая ячейка по счёту
        int number_of_width = 0;// число строк
        int item_number_of_width = 2;
        String exit = table_root( id,  item_number,  y,  x,  cells_in_height,  cells_in_width,  height,  width, style);
        while (how_many_cells_are_made < total_number_of_cells){
            item_number += 1;
            if (account_cell == cells_in_width){
                account_cell = 0;
            }

            if(number_of_width == 0 |
                    how_many_cells_are_made % cells_in_width == 0 & how_many_cells_are_made / cells_in_width == number_of_width){//тут нужна логика для создания строк
                exit += table_string( id, item_number,  cells_in_height,  height,  width, number_of_width, style);
                item_number_of_width = item_number;
                number_of_width += 1;
            }
            else {
                exit += table_cell( id,  item_number,  the_value_of_all_cells.get(how_many_cells_are_made),  height,  width, account_cell, item_number_of_width, style);
                how_many_cells_are_made += 1;
                account_cell += 1;
            }

        }
        return exit;
    }

    private static String table_root(String id, int item_number, int x, int y, int cells_in_height, int cells_in_width, int height, int width, int style){
        //начало таблицы
        String indent1 = "        ";
        String indent2 = "          ";
        String basics_of_the_table = style_root(style, id, item_number)
                + "\n" + indent2 + "<mxGeometry x=\"" + x + "\" y=\"" + y + "\" width=\"" + width * cells_in_height + "\" height=\"" + height * cells_in_width + "\" as=\"geometry\" />"
                + "\n" + indent1 + "</mxCell>";
        return basics_of_the_table;
    }
    private static String table_string(String id, int item_number, int cells_in_height, int height, int width, int number_of_width, int style){
        //строка
        String indent1 = "        ";
        String indent2 = "          ";
        String basis_of_the_string = style_string(style, id, item_number) ;
        if (number_of_width != 0){
            basis_of_the_string += "\n" + indent2 + "<mxGeometry" + " y=\"" + width * number_of_width + "\" width=\"" + width * cells_in_height + "\" height=\"" + height + "\" as=\"geometry\" />";
        }
        else {
            basis_of_the_string += "\n" + indent2 + "<mxGeometry width=\"" + height * cells_in_height + "\" height=\"" + height + "\" as=\"geometry\" />";
        }
        basis_of_the_string += "\n" + indent1 + "</mxCell>";
        return basis_of_the_string;
    }
    private static String table_cell(String id, int item_number, String cell_value, int height, int width, int account_cell, int number_of_width, int style){
        //ячейка
        String indent1 = "        ";
        String indent2 = "          ";
        String indent3 = "            ";
        String base_of_the_cell = style_cell(style, id, item_number, cell_value, number_of_width );
        if (account_cell != 0){
            base_of_the_cell += "\n" + indent2 + "<mxGeometry " + "x=\"" + width * account_cell + "\" width=\"" + width +"\" height=\"" + height +"\" as=\"geometry\">";
        }
        else {
            base_of_the_cell += "\n" + indent2 + "<mxGeometry width=\"" + width +"\" height=\"" + height +"\" as=\"geometry\">";
        }
        base_of_the_cell += "\n" + indent3 + "<mxRectangle width=\"" + width +"\" height=\"" + height +"\" as=\"alternateBounds\" />" +
                "\n" + indent2 + "</mxGeometry>" +
                "\n" + indent1 + "</mxCell>";
        return base_of_the_cell;
    }

    //стиль страницы
    private static String style_root(int choice , String id, int item_number ){
        String indent1 = "        ";
        String basis_of_the_string ="";
        if (choice == 1){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=table;startSize=0;container=1;collapsible=0;childLayout=tableLayout;fontSize=16;\" parent=\"1\" vertex=\"1\">";

        }else if(choice == 2){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=table;html=1;whiteSpace=wrap;startSize=0;container=1;collapsible=0;childLayout=tableLayout;columnLines=0;rowLines=0;fontSize=16;strokeColor=default;\" vertex=\"1\" parent=\"1\">";

        }else if(choice == 3){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=table;html=1;whiteSpace=wrap;startSize=0;container=1;collapsible=0;childLayout=tableLayout;columnLines=0;rowLines=0;fontSize=16;strokeColor=default;\" vertex=\"1\" parent=\"1\">";

        }else if(choice == 4){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"childLayout=tableLayout;recursiveResize=0;shadow=0;fillColor=none;strokeColor=#C0C0C0;\" vertex=\"1\" parent=\"1\">";
        }else if(choice == 5){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"childLayout=tableLayout;recursiveResize=0;shadow=0;fillColor=none;\" vertex=\"1\" parent=\"1\">";
        }else {
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=table;startSize=0;container=1;collapsible=0;childLayout=tableLayout;fontSize=16;\" parent=\"1\" vertex=\"1\">";
        }
        return basis_of_the_string;
    }
    private static String style_string(int choice , String id, int item_number){
        String indent1 = "        ";
        String basis_of_the_string ="";
        if (choice == 1){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontSize=16;\" parent=\"" + id +"-1\" vertex=\"1\">" ;

        }else if(choice == 2){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontSize=16;strokeColor=inherit;\" vertex=\"1\" parent=\"" + id +"-1\">" ;

        }else if(choice == 3){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontSize=16;\" vertex=\"1\" parent=\"" + id +"-1\">" ;

        }else if(choice == 4){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;top=0;left=0;bottom=0;right=0;dropTarget=0;collapsible=0;recursiveResize=0;expand=0;fontStyle=0;fillColor=none;\" vertex=\"1\" parent=\"" + id +"-1\">" ;

        }else if(choice == 5){
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" style=\"shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;top=0;left=0;bottom=0;right=0;dropTarget=0;collapsible=0;recursiveResize=0;expand=0;fontStyle=0;fillColor=none;strokeColor=inherit;\" vertex=\"1\" parent=\"" + id +"-1\">" ;

        }else{
            basis_of_the_string = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=tableRow;horizontal=0;startSize=0;swimlaneHead=0;swimlaneBody=0;strokeColor=inherit;top=0;left=0;bottom=0;right=0;collapsible=0;dropTarget=0;fillColor=none;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontSize=16;\" parent=\"" + id +"-1\" vertex=\"1\">" ;

        }
        return basis_of_the_string;
    }
    private static String style_cell(int choice , String id, int item_number, String cell_value, int number_of_width){
        String indent1 = "        ";
        String base_of_the_cell = "";
        if (choice == 1){
            base_of_the_cell = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"" + cell_value + "\" style=\"shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;fontSize=16;\" parent=\"" + id + "-" + number_of_width + "\" vertex=\"1\">";
        } else if(choice == 2){
            base_of_the_cell = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"" + cell_value + "\" style=\"shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;fillColor=none;top=0;left=0;bottom=0;right=0;overflow=hidden;fontSize=12;strokeColor=inherit;\" vertex=\"1\" parent=\"" + id + "-" + number_of_width + "\">";
        }else if(choice == 3){
            base_of_the_cell = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"" + cell_value + "\" style=\"shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;fontSize=16;\" vertex=\"1\" parent=\"" + id + "-" + number_of_width + "\">";

        }else if(choice == 4){
            base_of_the_cell = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"" + cell_value + "\" style=\"connectable=0;recursiveResize=0;strokeColor=none;fillColor=none;align=center;whiteSpace=wrap;html=1;\" vertex=\"1\" parent=\"" + id + "-" + number_of_width + "\">";

        }else if(choice == 5){
            base_of_the_cell = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"" + cell_value + "\"  style=\"connectable=0;recursiveResize=0;strokeColor=inherit;fillColor=none;align=center;whiteSpace=wrap;html=1;\" vertex=\"1\" parent=\"" + id + "-" + number_of_width + "\">";

        }else {
            base_of_the_cell = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"" + cell_value + "\" style=\"shape=partialRectangle;html=1;whiteSpace=wrap;connectable=0;strokeColor=inherit;overflow=hidden;fillColor=none;top=0;left=0;bottom=0;right=0;pointerEvents=1;fontSize=16;\" parent=\"" + id + "-" + number_of_width + "\" vertex=\"1\">";
        }
        return base_of_the_cell;
    }
}