package org.example.control_panel.web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.Converting_Tabular_Data;
import org.example.Main_Creating;

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
                // Pre-flight запрос, отвечаем необходимыми заголовками и пустым телом
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }

            if ("POST".equals(exchange.getRequestMethod())) {
                String requestBody = new String(exchange.getRequestBody().readAllBytes(), "utf-8");
                //входящие данные requestBody
                System.out.println(requestBody);

                Matcher table_in_the_text = Pattern.compile("\"table_in_the_text\":? ?\"([0-9a-zA-Zа-яА-Я\\\\t ]*)\",").matcher(requestBody);
                Matcher path_draw = Pattern.compile("\"path_draw\":? ?\"(.*.drawio)\"").matcher(requestBody);
                Matcher path_excel = Pattern.compile("\"path_excel\":? ?\"(.*.xlsx)\"").matcher(requestBody);
                Matcher matcher_height = Pattern.compile("\"height\":? ?(\\d*),").matcher(requestBody);
                Matcher matcher_width = Pattern.compile("\"width\":? ?(\\d*),").matcher(requestBody);
                Matcher matcher_x = Pattern.compile("\"x\":? ?(\\d*),").matcher(requestBody);
                Matcher matcher_y = Pattern.compile("\"y\":? ?(\\d*),").matcher(requestBody);


                //проверка данных
                Pattern check_path = Pattern.compile("\\\\.*?[\\/\\|:\\?\"<>].*?\\\\");
                Pattern check_table_text = Pattern.compile("([\\wа-яА-я]*\\\\t|[\\wа-яА-я][^ ]* |\\w[^ ]$)*");

                if( !table_in_the_text.matches() || !path_draw.matches() || !path_excel.matches() ||
                        !matcher_height.matches() || !matcher_width.matches() || !matcher_y.matches() || !matcher_x.matches()){
                    System.out.println("Ошибка: неверные входные данные");
                }
                else if ( check_path.matcher( path_draw.group() ).matches() || check_path.matcher( path_excel.group() ).matches()){
                    System.out.println("Ошибка: путь имеет невозможные  символы");
                }
                else if(!check_table_text.matcher( table_in_the_text.group() ).matches()){
                    System.out.println("Ошибка: текст таблицы неправильный или повреждённый");
                }
                //основное тело программы
                //c_t_d.JSON_to_normal_string()  // return String
//                if (matcher_height.find() & matcher_width.find() & matcher_y.find() & matcher_x.find()) {
//                    m_c.work(path_draw.group() , Integer.parseInt( matcher_height.group() ), Integer.parseInt( matcher_width.group() ), Integer.parseInt( matcher_x.group() ), Integer.parseInt( matcher_y.group() ) );
//                }
//                else {
//                    m_c.work( path_draw.group() );
//                }

                //формируем ответ
                String response = "{\"response\": \"Обработано ваше сообщение\"}";
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();


                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}