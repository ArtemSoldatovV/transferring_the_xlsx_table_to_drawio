package org.example.checking_the_input_data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checking_The_Input_Table {
    public boolean check(String txt){

        String[] fff = txt.split("\\n");
        int number_of_tabs = fff[0].split("\\t").length;

        for (String i : fff){
            if (number_of_tabs != i.split("\t").length){
                return false;
            }
        }

        return true;
    }

    public boolean check(String name_list, String excel_position){
        Pattern pattern_excel_position = Pattern.compile("([a-zA-Z]*)([0-9]*):([a-zA-Z]*)([0-9]*)");
        Matcher matcher_excel_positions = pattern_excel_position.matcher(excel_position);

        if(name_list.length() > 31 || name_list.isEmpty()){
            return false;
        }else if(Pattern.compile("[^,\\\\\\/\\?\\*\\[\\]]*").matcher(name_list).find()){
            return false;
        }

        if (!matcher_excel_positions.find()){
            return false;
        }
        else if(Integer.parseInt(matcher_excel_positions.group(2)) <= 0 || Integer.parseInt(matcher_excel_positions.group(4)) <= 0){
            return false;
        }

        return true;
    }


}
