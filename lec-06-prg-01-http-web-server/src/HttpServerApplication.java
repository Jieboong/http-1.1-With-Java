import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HttpServerApplication {
    public static void main(String[] args) {

        String hostAddress = "localhost";
        Integer portAddress = 9999;

        InetSocketAddress mySocketAddress = new InetSocketAddress(hostAddress, portAddress);

        try{
            HttpServer myServer = HttpServer.create(mySocketAddress, 0);
            myServer.createContext("/", new MyHttpHandler());
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            myServer.setExecutor(threadPoolExecutor);
            myServer.start();

        } catch (IOException e) {
            System.out.println("Wrong Connection");
            System.exit(1);
        }
    }
}
