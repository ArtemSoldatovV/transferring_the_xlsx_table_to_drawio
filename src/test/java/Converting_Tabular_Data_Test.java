import org.example.Converting_Tabular_Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.opentest4j.AssertionFailedError;

public class Converting_Tabular_Data_Test {
    Converting_Tabular_Data ctd = new Converting_Tabular_Data();
    @Test
    public void JSON_to_normal_string(){
        String the_original_text = "1\\ta\\2 2\\tb\\t4 3\\tc\\t9";
        String expected_result_text = "1\\ta\\2\\n2\\tb\\t4\\n3\\tc\\t9";
        String result_function = ctd.JSON_to_normal_string(the_original_text);
        assertEquals(true , expected_result_text.equals(result_function)     );
    }
    @Test
    public void reading_from_text(){
        ctd.reading_from_text("1\\ta\\2\\n2\\tb\\t4\\n3\\tc\\t9");
        int must_number_width = 3;
        int must_number_height = 3;
        ArrayList<String> must_height_width = new ArrayList<>(Arrays.asList( "1", "a", "2", "2", "b", "4", "3", "c", "9" ));
        assertEquals(true , ctd.getNumber_width() == must_number_width && ctd.getNumber_height() == must_number_height && ctd.getHeight_width() == must_height_width     );
    }
    @Test
    public void reading_from_excel(){
        String path_to_the_program = System.getProperty("user.dir");
        String path_to_the_test = "src\\test\\files";
        String name_of_the_excel_file = path_to_the_program + "\\" + path_to_the_test + "\\" + "test.xlsx";
        String name_sheet = "Sheet1";
        int columnIndex_star = 0;
        int columnIndex_end = 2;
        int startRow = 0;
        int endRow = 2;
        ctd.reading_from_excel(name_of_the_excel_file, name_sheet, columnIndex_star, columnIndex_end, startRow, endRow);

        int must_number_width = 3;
        int must_number_height = 3;
        ArrayList<String> must_height_width = new ArrayList<>(Arrays.asList( "1", "a", "7", "2", "b", "8", "3", "c", "9" ));
        assertEquals(true , ctd.getNumber_width() == must_number_width && ctd.getNumber_height() == must_number_height && ctd.getHeight_width().equals(must_height_width)     );
    }
}
