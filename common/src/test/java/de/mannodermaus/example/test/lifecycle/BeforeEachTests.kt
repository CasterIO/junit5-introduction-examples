package de.mannodermaus.example.test.lifecycle

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BeforeEachTests {

    @BeforeEach
    fun beforeEach() {
        println("@BeforeEach")
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
