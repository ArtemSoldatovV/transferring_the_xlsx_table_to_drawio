import org.example.Creating_Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Creating_Table_Test {
    Creating_Table ct = new Creating_Table();
    //возможно не нужен
//    @Test
//    public void table_root(){
//        String id = "i5xR082weA8DU3b7qYcW";
//        int item_number = 1;
//        int x = 0; int y = 0;
//        int cells_in_height = 3 ;
//        int cells_in_width = 3;
//        int height = 40; int width = 60;
//        String result = ct.table_root(id, item_number, x, y,cells_in_height, cells_in_width, height, width);
//        String indent1 = "        ";
//        String indent2 = "          ";
//        String check = "\n" + indent1 + "<mxCell id=\"" + id + "-" + item_number + "\" value=\"\" style=\"shape=table;startSize=0;container=1;collapsible=0;childLayout=tableLayout;fontSize=16;\" parent=\"1\" vertex=\"1\">"
//                + "\n" + indent2 + "<mxGeometry x=\"" + x + "\" y=\"" + y + "\" width=\"" + width * cells_in_height + "\" height=\"" + height * cells_in_width + "\" as=\"geometry\" />"
//                + "\n" + indent1 + "</mxCell>";
//        assertEquals(check, result );
//    }
    @Test
    public void create(){
        ArrayList<String> the_value_of_all_cells = new ArrayList<>(Arrays.asList( "1", "a", "2", "2", "b", "4", "3", "c", "9" ));
        int x = 0; int y = 0;
        int cells_in_height = 3;
        int cells_in_width = 3;
        int height = 40; int width = 60;
        String result = ct.create(the_value_of_all_cells, x, y, cells_in_height, cells_in_width,height, width, 1);
        //надо по строчно проверить
        // значение id // id="[\w0-9]*-[0-9]*"
        // x и y // " x=\" " x="[0-9]*\"" // " y=\" " y=\"[0-9]*\""
        // height и width // " height=\"" " height="[0-9]*\"" // " width=\"" " width="[0-9]*\""
        Pattern search_id = Pattern.compile(" id=\"");
        Pattern check_id = Pattern.compile(" id=\"[\\w0-9]*-[0-9]*\"");
        Pattern search_x = Pattern.compile(" x=\"");
        Pattern check_x = Pattern.compile(" x=\"[0-9]*\"");
        Pattern search_y = Pattern.compile(" y=\"");
        Pattern check_y = Pattern.compile(" y=\"[0-9]*\"");
        Pattern search_height = Pattern.compile(" height=\"");
        Pattern check_height = Pattern.compile(" height=\"[0-9]*\"");
        Pattern search_width = Pattern.compile(" width=\"");
        Pattern check_width = Pattern.compile(" width=\"[0-9]*\"");

        Boolean check = true;
        String[] result_split = result.split("\n");
        for (String r : result_split){
            if (search_id.matcher(r).matches()){
                if (!check_id.matcher(r).matches()){check = false;}
            }
            if (search_x.matcher(r).matches()){
                if (!check_x.matcher(r).matches()){check = false;}
            }
            if (search_y.matcher(r).matches()){
                if (!check_y.matcher(r).matches()){check = false;}
            }
            if (search_height.matcher(r).matches()){
                if (!check_height.matcher(r).matches()){check = false;}
            }
            if (search_width.matcher(r).matches()){
                if (!check_width.matcher(r).matches()){check = false;}
            }

        }
        assertEquals(true, check);
    }

}
