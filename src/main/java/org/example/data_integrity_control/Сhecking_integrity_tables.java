package org.example.data_integrity_control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Сhecking_integrity_tables {
    private Pattern pattern_root(String id_table, int item_number){
        String indent1 = "        ";
        Pattern exit = Pattern.compile(indent1 + "<mxCell id=\"[0-9a-zA-Z]*-" + item_number + "\" value=\".*?\" style=\".*;\" parent=\"1\" vertex=\"1\">|<mxCell id=\"[0-9a-zA-Z]*-" + item_number + "\" value=\".*?\" style=.*;\" vertex=\"1\" parent=\"1\">");
        return exit;
    }
    private Pattern string_root(String id_table, int item_number){
        String indent1 = "        ";
        Pattern exit = Pattern.compile(indent1 + "<mxCell id=\"[0-9a-zA-Z]*-" + item_number + "\" value=\".*?\" style=\".*;\" parent=\"[0-9a-zA-Z]*\" vertex=\"1\">");
        return exit;
    }
    private Pattern cell_root(String id_table, int item_number){
        String indent1 = "        ";
        Pattern exit = Pattern.compile(indent1 + "<mxCell id=\"[0-9a-zA-Z]*-" + item_number + "\" value=\".*?\" style=\".*?\" parent=\"[0-9a-zA-Z]*-" + item_number + "]\" vertex=\"1\">");
        return exit;
    }

    public boolean chek_tabel(String table){
        boolean y_n = true;

        String indent1 = "        ";
        String indent2 = "          ";
        String indent3 = "            ";

        //root_definition
        //indent3
        //define_width_height // width 1 height 2
        //<mxCell id="([a-zA-z0-9]*)-[0-9]" value=".*?" style=".*;" parent="1" vertex="1">
        Pattern pattern_id_table = Pattern.compile( "id=\"([a-zA-z0-9]*)");
        Matcher matcher_id_table = pattern_id_table.matcher(table);


        Pattern root_definition_line1 = Pattern.compile(indent1 + "<mxCell id=\"[0-9a-zA-Z]*-[0-9]\" value=\".*?\" style=\".*;\" parent=\"1\" vertex=\"1\">|<mxCell id=\"[0-9a-zA-Z]*-[0-9]\" value=\".*?\" style=.*;\" vertex=\"1\" parent=\"1\">");
        Pattern root_definition_line2 = Pattern.compile(indent2 + "/<mxGeometry x=\"[0-9]*\" y=\"[0-9]*\" width=\"[0-9]*\" height=\"[0-9]*\" as=\"geometry\" \\/>/gm");
        //string_definition
        Pattern string_definition_line1 = Pattern.compile(indent1 + "<mxCell id=\"[0-9a-zA-Z]*-[0-9]\" value=\".*?\" style=\".*;\" parent=\"[0-9a-zA-Z]*\" vertex=\"1\">");
        Pattern string_definition_line2_1 = Pattern.compile(indent2 + "<mxGeometry width=\"[0-9]*\" height=\"[0-9]*\" as=\"geometry\" \\/>");
        Pattern string_definition_line2_2 = Pattern.compile(indent2 + "<mxGeometry y=\"([0-9]*)\" width=\"[0-9]*\" height=\"[0-9]*\" as=\"geometry\" \\/>");

        //cell_definition
        Pattern cell_definition_line1 = Pattern.compile(indent1 + "<mxCell id=\"[0-9a-zA-Z]*-[0-9]\" value=\".*?\" style=\".*?\" parent=\"[0-9a-zA-Z]*-[0-9]\" vertex=\"1\">");
        Pattern cell_definition_line2_1 = Pattern.compile(indent2 + "<mxGeometry width=\"[0-9]*\" height=\"[0-9]*\" as=\"geometry\">");
        Pattern cell_definition_line2_2 = Pattern.compile(indent2 + "<mxGeometry x=\"([0-9]*)\" width=\"[0-9]*\" height=\"[0-9]*\" as=\"geometry\">");
        Pattern cell_definition_line3 = Pattern.compile(indent3 + "<mxRectangle width=\"[0-9]*\" height=\"[0-9]*\" as=\"alternateBounds\" \\/>");
        Pattern cell_definition_line2_end = Pattern.compile(indent2 + "</mxGeometry>");

        Pattern end_line = Pattern.compile(indent1 + "</mxCell>");

        /////////////////////////
        //System.out.print(table);
        String[] table_split = table.split("\n");
        // ищем id
        // берём из 1 элемента потому, что элемент 0 это пустая строка
        String id_table ="";
        if (pattern_id_table.matcher(table_split[1]).find()){
            id_table = table_split[1].substring(20,41);
        }else {y_n=false;}

        int line_number = 1;
        int item_number = 1;
        while (line_number < table_split.length && y_n != true){
//            System.out.print(item_number);
            System.out.print(table_split[line_number]);
            //проверяем root
            if (root_definition_line1.matcher(  table_split[line_number]  ).find()){
                boolean root_b_line1 = pattern_root( id_table, item_number ).matcher(  table_split[line_number]  ).find();
                boolean root_b_line2 = root_definition_line2.matcher(  table_split[line_number+1]  ).find();
                boolean root_b_line3 = end_line.matcher(  table_split[line_number+2]  ).find();
                if (false == root_b_line1 && root_b_line2 && root_b_line3){
                    y_n = false;
                }
                line_number += 3;
                item_number += 1;
            }else if (string_definition_line1.matcher(  table_split[line_number]  ).find()){
                boolean string_b_line1 = string_root( id_table, item_number ).matcher(  table_split[line_number]  ).find();
                boolean string_b_line2_1 = string_definition_line2_1.matcher(  table_split[line_number+1]  ).find();
                boolean string_b_line2_2 = string_definition_line2_2.matcher(  table_split[line_number+1]  ).find();
                boolean string_b_line3 = end_line.matcher(  table_split[line_number+2]  ).find();
                if (false == string_b_line1 && string_b_line3 && (string_b_line2_1 || string_b_line2_2) ){
                    y_n = false;
                }
                line_number += 3;
                item_number += 1;
            }else if (cell_definition_line1.matcher(  table_split[line_number]  ).find()){

                boolean cell_b_line1 =  cell_root( id_table, item_number ).matcher(  table_split[line_number]  ).find();
                boolean cell_b_line2_1 = cell_definition_line2_1.matcher(  table_split[line_number+1]  ).find();
                boolean cell_b_line2_2 = cell_definition_line2_2.matcher(  table_split[line_number+1]  ).find();
                boolean cell_b_line3 = cell_definition_line3.matcher(  table_split[line_number+2]  ).find();
                boolean cell_b_line4 = cell_definition_line2_end.matcher(  table_split[line_number+3]  ).find();
                boolean cell_b_line5 = end_line.matcher(  table_split[line_number+4]  ).find();

                if (false == cell_b_line1 && cell_b_line3 && cell_b_line4 && cell_b_line5 && (cell_b_line2_1 || cell_b_line2_2) ){
                    y_n = false;
                }
                line_number += 5;
                item_number += 1;
            }
            else {
                System.out.print("error");
                y_n = false;
            }
        }

        return y_n;
    }
}
