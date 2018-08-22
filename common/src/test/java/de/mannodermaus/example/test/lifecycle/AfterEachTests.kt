package de.mannodermaus.example.test.lifecycle

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class AfterEachTests {

    @AfterEach
    fun afterEach() {
        println("@AfterEach")
    }

    @Test
    fun test1() {
        println("   Test 1")
    }

    @Test
    fun test2() {
        println("   Test 2")
    }
}
