import okhttp3.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyHttpClient {
    public static void main(String[] args) throws URISyntaxException {
        System.out.println("## HTTP client started.");

        System.out.println("## GET request for http://localhost:9999/temp/");

        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

        // GET Request for URI
        try {
            String response = httpClient.sendAsync(
                    HttpRequest.newBuilder(
                                    new URI("http://localhost:9999/temp/"))
                            .GET().build(), HttpResponse.BodyHandlers.ofString()
            ).thenApply(HttpResponse::body).get();
            System.out.println("## GET response [start]");
            System.out.println(response);
            System.out.println("## GET response [end]");
        } catch (Exception e) {
            System.out.println("Wrong Request");
        }
        System.out.println("## GET request for http://localhost:9999/?var1=9&var2=9");

        // GET Request with queries
        try {
            String response = httpClient.sendAsync(
                    HttpRequest.newBuilder(
                                    new URI("http://localhost:9999/?var1=9&var2=9"))
                            .GET().build(), HttpResponse.BodyHandlers.ofString()
            ).thenApply(HttpResponse::body).get();
            System.out.println("## GET response [start]");
            System.out.println("response = " + response);
            System.out.println("## GET response [end]");

        } catch (Exception e) {
            System.out.println("Wrong Request");
        }

        System.out.println("## POST request for http://localhost:9999/ with var1 is 9 and var2 is 9");
        String params = "var1=9&var2=9";
        try {
            System.out.println("## POST response [start]");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "var1=9&var2=9");
            Request request = new Request.Builder()
                    .url("http://localhost:9999/")
                    .method("POST", body)
                    .addHeader("Content-Type", "text/plain")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println("response = " + response.body().string());
            System.out.println("## POST response [end]");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Wrong Request");
        }
        System.exit(0);
    }
}
