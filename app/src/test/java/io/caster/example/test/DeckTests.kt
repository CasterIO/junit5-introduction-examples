package io.caster.example.test

import io.caster.example.Deck
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DeckTests {

    @Test
    fun givenADeck_whenNoCardsAreAdded_itIsEmpty() {
        val deck = Deck(cards = emptyList())
        assertTrue(deck.isEmpty())
    }
}
