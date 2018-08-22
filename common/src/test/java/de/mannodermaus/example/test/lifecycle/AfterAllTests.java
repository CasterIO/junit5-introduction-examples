package de.mannodermaus.example.test.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class AfterAllTests {

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @Test
    void test1() {
        System.out.println("Test 1");
    }

    @Test
    void test2() {
        System.out.println("Test 2");
    }
}
