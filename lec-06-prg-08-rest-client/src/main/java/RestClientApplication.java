import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class RestClientApplication {

    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException, ParseException {

        // Reads a non registered member : error-case
        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0001")
                ).GET().build(),
                HttpResponse.BodyHandlers.ofString()
        ).get();

        int statusCode = response.statusCode();
        JSONParser parser = new JSONParser();
        JSONObject responseData = (JSONObject) parser.parse(response.body());

        System.out.println("#1 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0001"));

        JSONObject requestData = new JSONObject();
        requestData.put("0001", "apple");
        // Creates a new registered member : non-error case
        response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0001")
                ).setHeader("Content-type", "application/json"
                ).PUT(
                        HttpRequest.BodyPublishers.ofString(requestData.toJSONString())
                ).build(),
                HttpResponse.BodyHandlers.ofString()
        ).get();

        statusCode = response.statusCode();
        responseData = (JSONObject) parser.parse(response.body());

        System.out.println("#2 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0001"));

        // Reads a registered member : non-error case
        response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0001")
                ).GET().build(),
                HttpResponse.BodyHandlers.ofString()
        ).get();

        statusCode = response.statusCode();
        responseData = (JSONObject) parser.parse(response.body());

        System.out.println("#3 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0001"));

        requestData.put("0001", "xpple");

        //Creates an already registered member : error case
        response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0001")
                ).setHeader("Content-type", "application/json"
                ).PUT(
                        HttpRequest.BodyPublishers.ofString(requestData.toJSONString())
                ).build(),
                HttpResponse.BodyHandlers.ofString()
        ).get();

        statusCode = response.statusCode();
        responseData = (JSONObject) parser.parse(response.body());
        System.out.println("#4 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0001"));

        JSONObject requestData2 = new JSONObject();
        requestData2.put("0002", "xrange");

        // Updates a non registered member : error case
        response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0002")
                ).setHeader("Content-type", "application/json"
                ).POST(
                        HttpRequest.BodyPublishers.ofString(requestData2.toJSONString())
                ).build(),
                HttpResponse.BodyHandlers.ofString()
        ).get();

        statusCode = response.statusCode();
        responseData = (JSONObject) parser.parse(response.body());
        System.out.println("#5 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0002"));

        //Updates a registered member : non-error case
        JSONObject requestData3 = new JSONObject();
        requestData3.put("0002", "orange");

        client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0002")
                ).setHeader("Content-type", "application/json"
                ).PUT(
                        HttpRequest.BodyPublishers.ofString(requestData2.toJSONString())
                ).build(),
                HttpResponse.BodyHandlers.ofString()
        ).get();

        response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0002")
                ).setHeader("Content-type", "application/json"
                ).POST(
                        HttpRequest.BodyPublishers.ofString(requestData3.toJSONString())
                ).build(),
                HttpResponse.BodyHandlers.ofString()
        ).get();


        statusCode = response.statusCode();
        responseData = (JSONObject) parser.parse(response.body());
        System.out.println("#6 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0002"));

        // Delete a registered member : non-error case
        response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0001")
                ).DELETE().build()
                ,HttpResponse.BodyHandlers.ofString()
        ).get();

        statusCode = response.statusCode();
        responseData = (JSONObject) parser.parse(response.body());
        System.out.println("#7 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0001"));

        // Delete a registered member : non-error case
        response = client.sendAsync(
                HttpRequest.newBuilder(
                        new URI("http://localhost:8080/membership_api/0002")
                ).DELETE().build()
                ,HttpResponse.BodyHandlers.ofString()
        ).get();

        statusCode = response.statusCode();
        responseData = (JSONObject) parser.parse(response.body());
        System.out.println("#7 Code: " + statusCode + " >> JSON: " + responseData.toJSONString() + " >> JSON Result: " + responseData.get("0002"));

    }
}
