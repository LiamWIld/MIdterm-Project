package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        log.info("Starting application...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> respond(exchange, "Hello Final Project"));
        server.createContext("/health", exchange -> respond(exchange, "OK"));

        server.start();

        log.info("Server started on port 8080");
    }

    private static void respond(HttpExchange exchange, String msg) throws IOException {
        exchange.sendResponseHeaders(200, msg.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(msg.getBytes());
        os.close();
    }
}
