package org.example.control_panel.web;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                String requestBody = new String(exchange.getRequestBody().readAllBytes(), "utf-8");
                //входящие данные requestBody
                System.out.println(requestBody);

                //"Received".*"(.*)",?
                //String file_path_and_or_name, int height, int width, int y, int x
                //"file_path_and_or_name".*"(.*)".*?
                //"height".*(\d).*?
                //"width".*(\d).*?
                //"y".*(\d).*?
                //"x".*(\d).*?

//                Matcher matcher = Pattern.compile("Java(\\w*)").matcher("");
//                Boolean matcher2 = Pattern.compile("Java(\\w*)").matcher("").find();
                Matcher matcher_height = Pattern.compile("\"height\".*(\\d).*?").matcher(requestBody);
                Matcher matcher_width = Pattern.compile("\"width\".*(\\d).*?").matcher(requestBody);
                Matcher matcher_y = Pattern.compile("\"y\".*(\\d).*?").matcher(requestBody);
                Matcher matcher_x = Pattern.compile("\"x\".*(\\d).*?").matcher(requestBody);
                if (matcher_height.find() & matcher_width.find() & matcher_y.find() & matcher_x.find()) {

                }

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