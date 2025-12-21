package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.example.error_handling.Error_output_to_user;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converting_Tabular_Data {

    Error_output_to_user eotu = Error_output_to_user.getInstance();

    private static final Logger logger = LogManager.getLogger(Converting_Tabular_Data.class);

    private ArrayList<String> height_width;
    private int number_height;
    private int number_width;


    //чтение из текста
    public void reading_from_text(String table_text){
        logger.info("создание таблицы из текста");

        table_text = table_text.replace("\\n", "\n").replace("\\t", "\t");
        String[] height_height = table_text.split("\\n");
        int number_height = height_height.length;
        ArrayList<String> height_width = new ArrayList<String>();

        int number_width = height_height[0].split("\\t").length;

        for (String i : height_height){

            for (String q : i.split("\\t")) {
                height_width.add( q );
            }

        }
        this.height_width = height_width;
        this.number_height = number_height;
        this.number_width = number_width;
    }

    public String JSON_to_normal_string(String JSON_text){
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher = pattern.matcher(JSON_text);
        String exit =  matcher.replaceAll("\n");
        return exit;
    }
//////////////////////////////////////////////
    //чтение из excel
    public void reading_from_excel(String name_of_the_excel_file, String name_sheet, int columnIndex_star, int columnIndex_end, int startRow, int endRow){
        try {
            logger.info("создание таблицы из файла");
            var book = loadWorkbook(name_of_the_excel_file);
            var height_width = readColumn( book.getSheet(name_sheet), columnIndex_star, columnIndex_end, startRow, endRow );
            var number_height = endRow - startRow;
            var number_width = columnIndex_end - columnIndex_star;

            this.height_width = height_width;
            this.number_height = number_height;
            this.number_width = number_width;
        } catch (IOException e) {
            eotu.entering_error(e.getMessage());

            logger.fatal("ошибка в reading_from_excel");
            throw new RuntimeException(e);
        }
    }

    // Загрузка Workbook из файла
    private static Workbook loadWorkbook(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return WorkbookFactory.create(fis);
        }catch (IOException e) {

            Error_output_to_user eotu = Error_output_to_user.getInstance();
            eotu.entering_error(e.getMessage());

            logger.fatal("чтение файла привело к ошибке по пути " + filePath);
            throw new RuntimeException(e);
        }

    }

    // Получение значения ячейки как строки
    private static String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return "";
        }
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Чтение столбца в диапазоне строк excel
    private static ArrayList<String> readColumn(Sheet sheet, int columnIndex_star, int columnIndex_end, int startRow, int endRow) {
        //тут ошибка
        ArrayList<String> values = new ArrayList<>();

        for (int i = startRow; i < endRow; i++) {
            Row row = sheet.getRow(i);

            for (int i2 = columnIndex_star; i2 < columnIndex_end; i2++){
                if (row != null) {
                    values.add(getCellValue(row, i2 ));
                } else {
                    values.add("");
                }
            }

        }

        return values;
    }


    public ArrayList<String> getHeight_width() {return height_width;}
    public void setHeight_width(ArrayList<String> height_width) {this.height_width = height_width;}

    public int getNumber_height() {return number_height;}
    public void setNumber_height(int number_height) {this.number_height = number_height;}

    public int getNumber_width() {return number_width;}
    public void setNumber_width(int number_width) {this.number_width = number_width;}
}
