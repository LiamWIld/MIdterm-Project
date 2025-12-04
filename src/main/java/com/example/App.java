package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    private static final long startTime = System.currentTimeMillis();
    private static int requestCount = 0;

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {
            try {
                respond(exchange, "Hello Final Project");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        server.createContext("/health", exchange -> {
            try {
                requestCount++;

                long uptime = System.currentTimeMillis() - startTime;

                String json =
                        "{\n" +
                                "  \"status\": \"UP\",\n" +
                                "  \"uptime_ms\": " + uptime + ",\n" +
                                "  \"requests\": " + requestCount + "\n" +
                                "}";

                respond(exchange, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        server.start();
        System.out.println("Server running on port 8080");
    }

    private static void respond(HttpExchange exchange, String msg) throws Exception {

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, msg.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(msg.getBytes());
        os.close();
    }
}
