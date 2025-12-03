package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Application started successfully");
        log.info(buildInfo());
        log.info("Response: {}", hello());
    }

    public static String hello() {
        log.info("hello() was called");
        return "Hello Final Project";
    }

    public static String buildInfo() {
        String build = System.getenv("BUILD_NUMBER");
        if (build == null) build = "LOCAL";
        return "Build: " + build;
    }

}
