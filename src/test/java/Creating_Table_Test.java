import org.example.Creating_Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Creating_Table_Test {
    Creating_Table ct = new Creating_Table();

    @Test
    public void create(){
        ArrayList<String> the_value_of_all_cells = new ArrayList<>(Arrays.asList( "1", "a", "2", "2", "b", "4", "3", "c", "9" ));
        int x = 0; int y = 0;
        int cells_in_height = 3;
        int cells_in_width = 3;
        int height = 40; int width = 60;
        String result = ct.create(the_value_of_all_cells, x, y, cells_in_height, cells_in_width,height, width, 1);

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
        //построчное проверка
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
