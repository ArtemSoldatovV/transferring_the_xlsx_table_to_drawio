import org.example.control_panel.web.SimpleHttpServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.sleep;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Scenario2 {

    @BeforeAll
    public void startServer() throws Exception {
        //запуск сервера в отдельном потоке
        Thread serverThread = new Thread(() -> {
            try {
                SimpleHttpServer.main(new String[]{});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        //ждём запуска сервера
        sleep(2000);
    }

    @Test
    public void Scenario_2() throws IOException, InterruptedException {

        String path_to_the_program = System.getProperty("user.dir");
        String path_to_the_test = "src\\test\\files";
        String path_to_test_draw = (path_to_the_program + "\\" + path_to_the_test + "\\" + "test.drawio").replace("\\", "\\\\");
        URL url = new URL("http://localhost:8080/process");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        String jsonInputString = "{"
                + "\"path_draw\":\"" + path_to_test_draw + "\","
                + "\"table_in_the_text\":\"1a72b83c94d105e116f12\","
                + "\"height\":100,\"width\":200,\"x\":10,\"y\":20,\"style\":1" + "}";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        Assertions.assertEquals(200, responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();
        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }
        in.close();
        //без sleep тест заканчивается быстрее программы
        Thread.sleep(1000);


    }
}
