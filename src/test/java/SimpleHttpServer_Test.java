import org.example.control_panel.web.SimpleHttpServer;
import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleHttpServer_Test {

    @BeforeAll
    public void startServer() throws Exception {
        // Запуск сервера в отдельном потоке
        Thread serverThread = new Thread(() -> {
            try {
                SimpleHttpServer.main(new String[]{});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        // Подождать, пока сервер запустится
        Thread.sleep(2000);
    }

    @Test
    public void testPostRequest() throws IOException {
        URL url = new URL("http://localhost:8080/process");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        String jsonInputString = "{\"height\": 100, \"width\": 200, \"x\": 10, \"y\": 20, "
                + "\"path_draw\": \"test.drawio\", \"style\": 1, "
                + "\"table_in_the_text\": \"1\ta\t7 2\tb\t8 3\tc\t9 4\td\t10 5\te\t11 6\tf\t12\"}";

        try(OutputStream os = connection.getOutputStream()) {
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

        Assertions.assertTrue(response.toString().contains("подтверждение сообщение пришло"));
    }
}