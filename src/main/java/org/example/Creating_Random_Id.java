package org.example;

public class Creating_Random_Id {

    public static String generation_id(){
        //примеры
        //-r2dthNOID2k0gEMgki2
        //i5xR082weA8DU3b7qYcW
        //12345678901234567890
        String max ="i5xR082weA8DU3b7qYcW";
        String id ="";

        for (int i =0; i <max.length(); i++ ){
            id += character_selection();
        }

        return id;
    }

    public static String character_selection(){
        // 45 -
        int[] EN = {65, 90, 97, 122};//A-65 Z-90 a-97 z-122
        int[] number = {48, 57};//0-48 9-57
        String id ="";
        if (rnd(0,5)==0){
            return "0";
        }
        else if(rnd(0,5)==1){
            return String.valueOf( (char) rnd(EN[0],EN[1]) );
        }
        else if(rnd(0,3)==2) {
            return String.valueOf( (char) rnd(EN[2],EN[3]) );
        }
        else {

            return String.valueOf( (char) rnd(number[0],number[1]) );
        }
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
