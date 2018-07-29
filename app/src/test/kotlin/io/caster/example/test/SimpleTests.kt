package io.caster.example.test

import io.caster.example.Card
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo

class Tests {

    @Test
    fun simpleTest(info: TestInfo) {
        println("What now: $info")
        val model = Card(suit = "♤", value = "7")
        assertEquals("7♤", model.toString())
    }

    @Test
    fun anotherTest(info: TestInfo) {
        println("My test: $info")
        assertEquals(12, 6 + 6)
    }
}
