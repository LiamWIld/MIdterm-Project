package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void helloShouldReturnFinalMessage() {
        assertEquals("Hello Final Project", App.hello());
    }
    @Test
    void buildInfoShouldReturnSomething() {
        assertTrue(App.buildInfo().startsWith("Build"));
    }

}
