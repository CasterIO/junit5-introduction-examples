package de.mannodermaus.example.test.simple

import de.mannodermaus.example.*
import org.junit.jupiter.api.*

class SimpleTests {

    @Test
    fun aDeckWithoutCardsReturnsTrueWhenAskedIfEmpty() {
        val deck = Deck(cards = emptyList())
        Assertions.assertTrue(deck.isEmpty())
    }
}
