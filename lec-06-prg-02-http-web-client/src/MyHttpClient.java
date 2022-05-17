import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

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
        String params = "?var1=9&var2=9";
        try {
            String uri = "http://localhost:9999/";

            String response = httpClient.sendAsync(
                    HttpRequest.newBuilder(
                            new URI(uri)
                    ).POST(HttpRequest.BodyPublishers.ofString(params)).build(), HttpResponse.BodyHandlers.ofString()
            ).thenApply(HttpResponse::body).get();
            System.out.println("## POST response [start]");
            System.out.println("response = " + response);
            System.out.println("## POST response [end]");

        } catch (Exception e) {
            System.out.println("Wrong Request");

        }
    }


}
