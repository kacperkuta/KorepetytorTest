import java.net.URI;
import java.net.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Generator {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static void createUser(int i) throws IOException, InterruptedException, ExecutionException {
        String json = "{ \"user\": { " +
                "\"name\": \"Test\", \"surname\": \"Test\", \"email\": \"mail133_" + i + "@test.pl\", " +
                "\"password\": \"haslo123\", \"phone_number\": \"123123123\", \"is_teacher\": \"False\" } }";

        // add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("https://kor.vavatech.pl/api/user/register/"))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    private static void createTeacher(int i) throws IOException, InterruptedException, ExecutionException {
        String json = "{ \"user\": { " +
                "\"name\": \"Test\", \"surname\": \"Test\", \"email\": \"mailt133_" + i + "@test.pl\", " +
                "\"password\": \"haslo123\", \"phone_number\": \"123123123\", \"is_teacher\": \"True\" } }";

        // add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("https://kor.vavatech.pl/api/user/register/"))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        for (int i = 1000; i < 21000; i++) {
            createUser(i);
            createTeacher(i);
        }

    }

}

