package de.mannodermaus.example.common.test.lifecycle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BeforeAllTests {

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
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
