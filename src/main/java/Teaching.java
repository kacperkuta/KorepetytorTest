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

public class Teaching {

    // enter your token here
    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjIzODc5NjYwLCJqdGkiOiI4MmEyZWEzNzNiZWY0OTUyOWIzOTVlNzYxNDEwMDVjOCIsInVzZXJfaWQiOjE4fQ.yEdI4YLIfIs96FRSL4gd1jfinMToEICfYxxKkJOVq6Y";

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static void addSubjects(int id) throws IOException, InterruptedException, ExecutionException {
        String json = "{ \"teacher\": " + id + ", \"subject\": \"polish\", \"level_from\": \"P1\"," +
                "\"level_to\": \"AC\", \"price\": 60 }";

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8000/user/fields/"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());

        json = "{ \"teacher\": " + id + ", \"subject\": \"maths\", \"level_from\": \"P1\"," +
                "\"level_to\": \"AC\", \"price\": 60 }";

        request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8000/user/fields/"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .build();

        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());

        json = "{ \"teacher\": " + id + ", \"subject\": \"english\", \"level_from\": \"P1\"," +
                "\"level_to\": \"AC\", \"price\": 60 }";

        request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8000/user/fields/"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .build();

        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());

    }

    private static void addDescriptions(int id) throws IOException, InterruptedException, ExecutionException {
        String json2 = "{\"id\": " + id + ", \"teacher_info\": { \"short_desc\": \"Opis\", \"long_desc\": \"Lorem ipsum dolor sit amet," +
                "consectetur adipiscing elit. Duis egestas sed felis ac sodales. Fusce venenatis leo nulla, sit amet gravida sem" +
                "accumsan a. In et libero ut diam dictum semper rhoncus eu nisl. Aenean felis odio, aliquet non arcu sit amet," +
                "ultrices ultrices tortor. Mauris accumsan, nunc eget hendrerit bibendum, turpis nulla maximus felis, et tincidunt" +
                " neque sem vitae metus. Nam hendrerit tellus eget nisl eleifend, nec tristique purus faucibus. Sed dolor tortor," +
                "cursus vitae aliquam vel, posuere eu nulla. Vivamus in nibh risus. Pellentesque habitant morbi tristique senectus" +
                "et netus et malesuada fames ac turpis egestas. Pellentesque sit amet mollis lorem. Proin vestibulum velit ut mi" +
                "blandit faucibus. Suspendisse varius, leo at ornare condimentum, mauris diam scelerisque elit, eget congue nisl" +
                "elit ac sem. Nam rutrum ac arcu sit amet molestie. Morbi quis malesuada quam, a pretium ligula blandit.\"}}";

        // add json header
        HttpRequest request2 = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(json2))
                .uri(URI.create("http://localhost:8000/user/teachers/"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .build();

        HttpResponse<String> response2 = httpClient.send(request2, HttpResponse.BodyHandlers.ofString());
        System.out.println(response2.statusCode());
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        // choose range of ids
        int start = 1;
        int end = 100;
        for (int i = start; i <= end; i++) {
            addDescriptions(i);
            addSubjects(i);
        }
    }

}
