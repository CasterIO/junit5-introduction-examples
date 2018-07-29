package io.caster.example.test

import io.caster.example.Card
import io.caster.example.Rank
import io.caster.example.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo

class Tests {

    @Test
    fun simpleTest(info: TestInfo) {
        println("What now: $info")
        val model = Card(rank = Rank.Num(7), suit = Suit.Spades)
        assertEquals("7♠", model.toString())
    }

    @Test
    fun anotherTest(info: TestInfo) {
        println("My test: $info")
        assertEquals(12, 6 + 6)
    }

    @Test
    fun checkInvalidRanks() {
        assertThrows(IllegalArgumentException::class.java) {
            // Invalid
            Rank.Num(1)
        }
        for (i in 2..10) {
            // Valid range
            Rank.Num(i)
        }
        assertThrows(IllegalArgumentException::class.java) {
            // Invalid
            Rank.Num(11)
        }
    }
}
