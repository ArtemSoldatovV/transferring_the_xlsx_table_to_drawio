package org.example.control_panel.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Converting_Tabular_Data;
import org.example.Main_Creating;
import org.example.error_handling.Error_output_to_user;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Desktop;

public class SimpleHttpServer {
    private static final Logger logger = LogManager.getLogger(SimpleHttpServer.class);

    public static void main(String[] args) throws IOException {
        // создаем сервер, слушающий порт 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // создаем контекст /process для обработки POST-запросов
        server.createContext("/process", new MyHandler());

        // запускаем сервер
        server.start();
        System.out.println("Server is listening on port 8080");

        File htmlFile = new File("src/main/java/org/example/control_panel/web/interface.html");
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(htmlFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.fatal("ошибка браузер не смог открыть страницу");
        }
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


                    Matcher matcher_height = Pattern.compile("\"height\":? ?([0-9]*),?").matcher(requestBody);
                    Matcher matcher_width = Pattern.compile("\"width\":? ?([0-9]*),?").matcher(requestBody);
                    Matcher matcher_x = Pattern.compile("\"x\":? ?([0-9]*),?").matcher(requestBody);
                    Matcher matcher_y = Pattern.compile("\"y\":? ?([0-9]*),?").matcher(requestBody);

                    Matcher name_of_the_draw_file = Pattern.compile("\"name_of_the_draw_file\":? ?\"(.*.drawio)\"").matcher(requestBody);
                    Matcher matcher_style = Pattern.compile("\"style\":? ?([0-9]*),?").matcher(requestBody);

                    Matcher text_table = Pattern.compile("\"text_table\":? ?\"(.*)\",").matcher(requestBody);

                    Matcher name_of_the_excel_file = Pattern.compile("\"name_of_the_excel_file\":? ?\"(.*.xlsx)\"").matcher(requestBody);
                    Matcher name_sheet = Pattern.compile("\"name_sheet\":? ?\"([0-9a-zA-Zа-яА-Я]*)\",?").matcher(requestBody);
                    Matcher coordinates_of_the_selected_table = Pattern.compile("\"coordinates_of_the_selected_table\":? ?\"([a-zA-Z]*[0-9]*:[a-zA-Z]*[0-9]*)\",?").matcher(requestBody);
                    //проверка данных
                    Pattern check_path = Pattern.compile("([A-Z]:[\\\\a-zA-Zа-яА-Я0-9 ]*[a-zA-Zа-яА-Я0-9 ]\\.[a-zA-Z0-9]*)");
                    Pattern check_table_text = Pattern.compile("([\\w\\Wа-яА-я]*\\\\t|[\\w\\Wа-яА-я][^ ]* |[\\w\\Wа-яА-я][^ ]*$)*");

                    //эти переменные нужны для нормальной работы проверки верности входных данных, логика на примую через .find() работает не каректно
                    boolean boolean_name_of_the_draw_file = name_of_the_draw_file.find();
                    boolean boolean_matcher_style = matcher_style.find();
                    boolean boolean_text_table  = text_table.find();
                    boolean boolean_name_of_the_excel_file = name_of_the_excel_file.find();
                    boolean boolean_name_sheet = name_sheet.find();


                    //group может вернуть String или IllegalStateException, что приводит к ошибками
                    String String_name_of_the_draw_file = "";
                    try {
                        String_name_of_the_draw_file = name_of_the_draw_file.group(1);
                    } catch (IllegalStateException e) {
                        String_name_of_the_draw_file = "";
                    }
                    String String_matcher_style = matcher_style.group(1);
                    try {
                        String_matcher_style = matcher_style.group(1);
                    } catch (IllegalStateException e) {
                        String_matcher_style = "";
                    }
                    String String_text_table  = "";
                    try {
                        String_text_table  = text_table .group(1);
                    } catch (IllegalStateException e) {
                        String_text_table  = "";
                    }
                    String String_name_of_the_excel_file = "";
                    try {
                        String_name_of_the_excel_file = name_of_the_excel_file.group(1);
                    } catch (IllegalStateException e) {
                        String_name_of_the_excel_file = "";
                    }
                    String String_name_sheet = "";
                    try {
                        String_name_sheet = name_sheet.group(1);
                    } catch (IllegalStateException e) {
                        String_name_sheet = "";
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

                    Boolean check_darw_path = check_path.matcher(String_name_of_the_draw_file).matches();

                    if (!check_darw_path) {
                        logger.error("Ошибка: путь до файл .drawio имеет невозможные символы");
                    } else {
                        //основное тело программы
                        if (boolean_text_table ) {
                            if (!check_table_text.matcher(String_text_table ).matches()) {
                                throw new IllegalArgumentException("Ошибка: текст таблицы неправильный или повреждённый");
                            } else {
                                String text = c_t_d.JSON_to_normal_string(String_text_table );
                                m_c.work(String_name_of_the_draw_file, text, Integer.parseInt(String_matcher_style)
                                        , Integer.parseInt(String_height), Integer.parseInt(String_width)
                                        , Integer.parseInt(String_x), Integer.parseInt(String_y));
                            }
                        } else {
                            if (!check_path.matcher(String_name_of_the_excel_file).matches()) {
                                throw new IllegalArgumentException("Ошибка: путь до файл .xlsx имеет невозможные символы");
                            } else {
                                m_c.work(String_name_of_the_draw_file, Integer.parseInt(String_matcher_style)
                                        , Integer.parseInt(String_height), Integer.parseInt(String_width)
                                        , Integer.parseInt(String_x), Integer.parseInt(String_y)
                                        , String_name_of_the_excel_file, String_name_sheet
                                        , String_coordinates_of_the_selected_table
                                );
                            }
                        }
                    }

                    Thread.sleep(1500);//нужен потому, что программа доходит до проверки раньше запись файла
                    Error_output_to_user eotu = Error_output_to_user.getInstance();
                    if (eotu.error_occurred()) {
                        String response = "{\"error_message\": \"" + eotu.error_output() +"\"}";
                        exchange.getResponseHeaders().add("Content-Type", "application/json");
                        exchange.sendResponseHeaders(500, response.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }
                    else {
                        //формируем ответ
                        String response = "{\"response\": \"подтверждение сообщение пришло\"}";
                        exchange.getResponseHeaders().add("Content-Type", "application/json");
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    }

                }catch (IllegalArgumentException | InterruptedException e) {
                    logger.error(e);

                    String response = "{\"error_message\": \"" + e.getMessage() +"\"}";
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(500, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                    throw new RuntimeException(e);
                }



            } else {
                logger.error("мы вернули ответ 405");
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}