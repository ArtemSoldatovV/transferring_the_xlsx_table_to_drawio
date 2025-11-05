package org.example.excel_positions;

import java.util.Map;

public class Converting_Letter_To_Number {
    Map<Character, Integer> letter_to_number =  Map.ofEntries(
            Map.entry('A', 1), Map.entry('B', 2),
            Map.entry('C', 3), Map.entry('D', 4),
            Map.entry('E', 5), Map.entry('F', 6),
            Map.entry('G', 7), Map.entry('H', 8),
            Map.entry('I', 9), Map.entry('J', 10),
            Map.entry('K', 11), Map.entry('L', 12),
            Map.entry('M', 13), Map.entry('N', 14),
            Map.entry('O', 15), Map.entry('P', 16),
            Map.entry('Q', 17), Map.entry('R', 18),
            Map.entry('S', 19), Map.entry('T', 20),
            Map.entry('U', 21), Map.entry('V', 22),
            Map.entry('W', 23), Map.entry('X', 24),
            Map.entry('Y', 25), Map.entry('Z', 26)
    );
    public Integer converting_string_to_number(String str){
        int exit_int = 0;
        for (String i : str.split("")){
            exit_int += letter_to_number.get(i.toUpperCase().charAt(0));
        }
        return exit_int;
    }

}
