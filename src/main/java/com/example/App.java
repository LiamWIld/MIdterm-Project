package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/health", new HealthHandler());
        server.createContext("/fail", new FailHandler());
        server.createContext("/", new RootHandler());

        log("startup", "Application started on port " + port);
        server.start();
    }

    static class HealthHandler implements HttpHandler {
        public void handle(HttpExchange exchange) {
            try {
                Map<String, String> data = new HashMap<>();
                data.put("status", "UP");
                data.put("build", getBuild());
                data.put("time", Instant.now().toString());

                String json = toJson(data);
                exchange.sendResponseHeaders(200, json.length());
                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();

                log("health", json);
            } catch (Exception e) {}
        }
    }

    static class FailHandler implements HttpHandler {
        public void handle(HttpExchange exchange) {
            log("failure", "Application crash triggered");
            System.exit(1);
        }
    }

    static class RootHandler implements HttpHandler {
        public void handle(HttpExchange exchange) {
            try {
                String msg = "Hello Final Project";
                exchange.sendResponseHeaders(200, msg.length());
                exchange.getResponseBody().write(msg.getBytes());
                exchange.getResponseBody().close();

                log("request", "/");
            } catch (Exception e) {}
        }
    }

    static String getBuild() {
        String b = System.getenv("BUILD_NUMBER");
        return b == null ? "LOCAL" : b;
    }

    static void log(String event, String message) {
        System.out.println("{\"event\":\"" + event + "\",\"build\":\"" + getBuild() + "\",\"message\":\"" + message + "\"}");
    }

    static String toJson(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("{");
        for (String k : map.keySet()) {
            sb.append("\"").append(k).append("\":\"").append(map.get(k)).append("\",");
        }
        return sb.substring(0, sb.length() - 1) + "}";
    }
}
