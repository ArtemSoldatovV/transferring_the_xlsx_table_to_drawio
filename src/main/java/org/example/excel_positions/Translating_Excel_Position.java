package org.example.excel_positions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translating_Excel_Position {
    private static final Logger logger = LogManager.getLogger(Translating_Excel_Position.class);
    public int[] converting_position_excel_to_number(String excel_position){
        try {
            int M_T_B_O_R_T_T = -1;//Mixing_the_beginning_of_reading_the_table

            Matcher matcher_excel_positions = Pattern.compile("([a-zA-Z]*)([0-9]*):([a-zA-Z]*)([0-9]*)").matcher(excel_position);
            Converting_Letter_To_Number c_l_t_n = new Converting_Letter_To_Number();
            int column_position_1 = 0;
            int line_position_1 = 0;
            int column_position_2 = 0;
            int line_position_2 = 0;
            if (matcher_excel_positions.find()) {
                column_position_1 = c_l_t_n.converting_string_to_number(matcher_excel_positions.group(1)) + M_T_B_O_R_T_T;
                line_position_1 = Integer.parseInt(matcher_excel_positions.group(2)) + M_T_B_O_R_T_T;
                column_position_2 = c_l_t_n.converting_string_to_number(matcher_excel_positions.group(3));
                line_position_2 = Integer.parseInt(matcher_excel_positions.group(4));
            }else {
                throw new IllegalArgumentException("неверно введенные данные");
            }
            int[] mas_exit = new int[]{
                    column_position_1, line_position_1
                    , column_position_2, line_position_2};
            return mas_exit;
        } catch (IllegalArgumentException e) {
            logger.fatal(e);
            throw new IllegalArgumentException(e);
        }
    }
}
