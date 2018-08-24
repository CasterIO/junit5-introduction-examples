package de.mannodermaus.example.common.test.test

import de.mannodermaus.example.common.Deck
import org.junit.jupiter.api.*

class SimpleTests {

    @Test
    fun aDeckWithoutCardsReturnsTrueWhenAskedIfEmpty() {
        val deck = Deck(cards = emptyList())
        Assertions.assertTrue(deck.isEmpty())
    }
}
