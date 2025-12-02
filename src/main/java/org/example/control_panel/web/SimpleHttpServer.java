package org.example.control_panel.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Converting_Tabular_Data;
import org.example.Main_Creating;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // Создаем сервер, слушающий порт 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Создаем контекст /process для обработки POST-запросов
        server.createContext("/process", new MyHandler());

        // Запускаем сервер
        server.start();
        System.out.println("Server is listening on port 8080");
    }

    static class MyHandler implements HttpHandler {

        private static final Logger logger = LogManager.getLogger(MyHandler.class);
        // Разрешить доступ с этого источника
        private static final String ALLOWED_ORIGIN = "http://localhost:63342";

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Main_Creating m_c = new Main_Creating();
            Converting_Tabular_Data c_t_d = new Converting_Tabular_Data();

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            String method = exchange.getRequestMethod();

            if ("OPTIONS".equalsIgnoreCase(method)) {
                //Pre-flight запрос, отвечаем необходимыми заголовками и пустым телом
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }

            if ("POST".equals(exchange.getRequestMethod())) {
                try {
                    String requestBody = new String(exchange.getRequestBody().readAllBytes(), "utf-8");
                    //входящие данные requestBody
                    System.out.println(requestBody);
                    //формируем ответ
                    String response = "{\"response\": \"подтверждение сообщение пришло\"}";
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                    Matcher matcher_height = Pattern.compile("\"height\":? ?([0-9]*),?").matcher(requestBody);
                    Matcher matcher_width = Pattern.compile("\"width\":? ?([0-9]*),?").matcher(requestBody);
                    Matcher matcher_x = Pattern.compile("\"x\":? ?([0-9]*),?").matcher(requestBody);
                    Matcher matcher_y = Pattern.compile("\"y\":? ?([0-9]*),?").matcher(requestBody);

                    Matcher path_draw = Pattern.compile("\"path_draw\":? ?\"(.*.drawio)\"").matcher(requestBody);
                    Matcher matcher_style = Pattern.compile("\"style\":? ?([0-9]*),?").matcher(requestBody);

                    Matcher table_in_the_text = Pattern.compile("\"table_in_the_text\":? ?\"(.*)\",").matcher(requestBody);

                    Matcher path_excel = Pattern.compile("\"path_excel\":? ?\"(.*.xlsx)\"").matcher(requestBody);
                    Matcher excel_sheet = Pattern.compile("\"excel_sheet\":? ?\"([0-9a-zA-Zа-яА-Я]*)\",?").matcher(requestBody);
                    Matcher coordinates_of_the_selected_table = Pattern.compile("\"coordinates_of_the_selected_table\":? ?\"([a-zA-Z]*[0-9]*:[a-zA-Z]*[0-9]*)\",?").matcher(requestBody);
                    //проверка данных
                    Pattern check_path = Pattern.compile("([A-Z]:[\\\\a-zA-Zа-яА-Я0-9 ]*[a-zA-Zа-яА-Я0-9 ]\\.[a-zA-Z0-9]*)");
                    Pattern check_table_text = Pattern.compile("([\\w\\Wа-яА-я]*\\\\t|[\\w\\Wа-яА-я][^ ]* |[\\w\\Wа-яА-я][^ ]*$)*");

                    //эти переменные нужны для нормальной работы проверки верности входных данных, логика на примую через .find() работает не каректно
                    boolean boolean_path_draw = path_draw.find();
                    boolean boolean_matcher_style = matcher_style.find();
                    boolean boolean_table_in_the_text = table_in_the_text.find();
                    boolean boolean_path_excel = path_excel.find();
                    boolean boolean_excel_sheet = excel_sheet.find();


                    //group может вернуть String или IllegalStateException, что приводит к ошибками
                    String String_path_draw = "";
                    try {
                        String_path_draw = path_draw.group(1);
                    } catch (IllegalStateException e) {
                        String_path_draw = "";
                    }
                    String String_matcher_style = matcher_style.group(1);
                    try {
                        String_matcher_style = matcher_style.group(1);
                    } catch (IllegalStateException e) {
                        String_matcher_style = "";
                    }
                    String String_table_in_the_text = "";
                    try {
                        String_table_in_the_text = table_in_the_text.group(1);
                    } catch (IllegalStateException e) {
                        String_table_in_the_text = "";
                    }
                    String String_path_excel = "";
                    try {
                        String_path_excel = path_excel.group(1);
                    } catch (IllegalStateException e) {
                        String_path_excel = "";
                    }
                    String String_excel_sheet = "";
                    try {
                        String_excel_sheet = excel_sheet.group(1);
                    } catch (IllegalStateException e) {
                        String_excel_sheet = "";
                    }

                    String String_coordinates_of_the_selected_table = "";
                    try {
                        boolean boolean_coordinates_of_the_selected_table = coordinates_of_the_selected_table.find();
                        String_coordinates_of_the_selected_table = coordinates_of_the_selected_table.group(1);
                    } catch (IllegalStateException e) {
                        String_coordinates_of_the_selected_table = "";
                    }

                    String String_height = "";
                    try {
                        boolean boolean_height = matcher_height.find(); //определение переменной boolean через .find() приводит к верной работе программы
                        String_height = matcher_height.group(1);
                    } catch (IllegalStateException e) {
                        String_height = "60";
                    }
                    String String_width = "";
                    try {
                        boolean boolean_width = matcher_width.find();
                        String_width = matcher_width.group(1);
                    } catch (IllegalStateException e) {
                        String_width = "40";
                    }
                    String String_x = "";
                    try {
                        boolean boolean_x = matcher_x.find();
                        String_x = matcher_x.group(1);
                    } catch (IllegalStateException e) {
                        String_x = "0";
                    }
                    String String_y = "";
                    try {
                        boolean boolean_y = matcher_y.find();
                        String_y = matcher_y.group(1);
                    } catch (IllegalStateException e) {
                        String_y = "0";
                    }

                    Boolean check_darw_path = check_path.matcher(String_path_draw).matches();

                    if (!check_darw_path) {
                        logger.error("Ошибка: путь до файлв .drawio имеет невозможные символы");
                    } else {
                        //основное тело программы
                        if (boolean_table_in_the_text) {
                            if (!check_table_text.matcher(String_table_in_the_text).matches()) {
                                throw new IllegalArgumentException("Ошибка: текст таблицы неправильный или повреждённый");
                            } else {
                                String text = c_t_d.JSON_to_normal_string(String_table_in_the_text);
                                m_c.work(String_path_draw, text, Integer.parseInt(String_matcher_style)
                                        , Integer.parseInt(String_height), Integer.parseInt(String_width)
                                        , Integer.parseInt(String_x), Integer.parseInt(String_y));
                            }
                        } else {
                            if (!check_path.matcher(String_path_excel).matches()) {
                                throw new IllegalArgumentException("Ошибка: путь до файлв .xlsx имеет невозможные символы");
                            } else {
                                m_c.work(String_path_draw, Integer.parseInt(String_matcher_style)
                                        , Integer.parseInt(String_height), Integer.parseInt(String_width)
                                        , Integer.parseInt(String_x), Integer.parseInt(String_y)
                                        , String_path_excel, String_excel_sheet
                                        , String_coordinates_of_the_selected_table
                                );
                            }
                        }
                    }
                }catch (IllegalArgumentException e) {
                    logger.error(e);
                    throw new RuntimeException(e);
                }

            } else {
                logger.error("мы вернули ответ 405");
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}