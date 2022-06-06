import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MyHttpHandler implements HttpHandler {

    // Request 연결인 HttpExchange를 Attribute로 가지는 클래스
    HttpExchange httpExchange;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        this.httpExchange = exchange;
        String requestMethod = httpExchange.getRequestMethod();
        //공통되는 printHttpRequestDetail, sendHttpResponseHeader 먼저 처리
        System.out.println("## handle " +requestMethod+ " activated");
        printHttpRequestDetail();
        sendHttpResponseHeader();

        // HttpHandler에서는 Method를 따로 구분하지 않으므로 Method 별로 함수 구현
        if (requestMethod.equals("GET")) {
            handleGET(exchange);
        }
        else if (requestMethod.equals("POST")){
            handlePOST(exchange);
        }
        else {
            System.out.println("Unsupported Method");
        }
    }

    private void printHttpRequestDetail() {
        System.out.println("::Client address    : " + httpExchange.getRemoteAddress().getAddress());
        System.out.println("::Client port       : " + httpExchange.getRemoteAddress().getPort());
        System.out.println("::Request command   : " + httpExchange.getRequestMethod());
        System.out.println("::Request line      : " + httpExchange.getRequestMethod() + " " + httpExchange.getRequestURI() + " " + httpExchange.getProtocol());
        System.out.println("::Request path      : " + httpExchange.getRequestURI());
        System.out.println("::Request version   : " + httpExchange.getProtocol());
    }

    private void sendHttpResponseHeader() throws IOException {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.set("Content-type", "text/html");
    }

    private void handleGET(HttpExchange exchange) throws IOException {
        String requestPath = exchange.getRequestURI().getPath();
        String query = exchange.getRequestURI().getRawQuery();
        //자원을 요청했을 경우
        if (query == null){

            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("<html>")
                    .append("<p>HTTP Request GET for Path:")
                    .append(requestPath)
                    .append("</p>")
                    .append("</html>");
            String responseHTML = responseBuilder.toString();

            httpExchange.sendResponseHeaders(200, responseHTML.length());

            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(responseHTML.getBytes(StandardCharsets.UTF_8));
            System.out.println("## GET requests for directory => " + exchange.getRequestURI().getPath());
            responseBody.close();
        }
        // query문 존재하는 경우
        else{
            List<Integer> variables = parameterRetrieval(query);
            Integer answer = simpleCalculation(variables.get(0), variables.get(1));
            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("<html>")
                    .append("GET request for calculation => ")
                    .append(variables.get(0))
                    .append(" X ") .append(variables.get(1))
                    .append(" = ").append(answer)
                    .append("</html>");

            String responseHTML = responseBuilder.toString();

            httpExchange.sendResponseHeaders(200, responseHTML.length());

            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(responseHTML.getBytes(StandardCharsets.UTF_8));

            System.out.println(
                    String.format(
                            "## GET requests for calculation => %d X %d = %d",
                            variables.get(0), variables.get(1), answer)
            );
        }
    }

    private void handlePOST(HttpExchange exchange) throws IOException {

        Integer contentLen = Integer.parseInt(exchange.getRequestHeaders().getFirst("Content-Length"));
        String postData = new String(exchange.getRequestBody().readNBytes(contentLen));

        List<Integer> parameter = parameterRetrieval(postData);
        Integer result = simpleCalculation(parameter.get(0), parameter.get(1));
        String postResponse = String.format(
                "POST request for calculation => %d X %d = %d",
                parameter.get(0), parameter.get(1), result
        );

        exchange.sendResponseHeaders(200, postResponse.length());
        exchange.getResponseBody().write(postResponse.getBytes(StandardCharsets.UTF_8));
        System.out.println("## POST request data => " + postData);
        System.out.println("## " + postResponse);
    }

    private List<Integer>parameterRetrieval(String msg) {
        List<Integer> variables = new ArrayList<>();
        String[] queries = msg.split("&");
        variables.add(Integer.parseInt(queries[0].split("=")[1]));
        variables.add(Integer.parseInt(queries[1].split("=")[1]));
        return variables;
    }

    private Integer simpleCalculation(Integer param1, Integer param2) {
        return param1 * param2;
    }
}
