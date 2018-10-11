package de.mannodermaus.example.common.test.lifecycle

import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class AfterEachTests {

    @Test
    fun test1() {
        println("Test 1: $this")
    }

    @Test
    fun test2() {
        println("Test 2: $this")
    }
}
