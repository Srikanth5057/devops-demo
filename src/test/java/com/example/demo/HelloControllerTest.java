package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {
    @Test
    void helloReturnsGreeting() {
        assertEquals("Hello DevOps", new HelloController().hello());
    }
}