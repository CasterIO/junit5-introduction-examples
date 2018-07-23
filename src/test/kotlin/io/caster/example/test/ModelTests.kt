package io.caster.example.test

import io.caster.example.Card
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ModelTests {

    @Test
    fun exampleTest() {
        val model = Card(suit = "♤", value = "7")
        assertEquals("7♤", model.toString())
    }
}
