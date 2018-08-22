package de.mannodermaus.example.test.assertions

import de.mannodermaus.example.Deck
import de.mannodermaus.example.EmptyDeckException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AssertThrowsTests {

    @Test
    @DisplayName("Trying to draw from an empty Deck will throw an Exception")
    fun drawingFromEmptyDeckThrowsException() {
        val deck = Deck(cards = emptyList())
        assertThrows<EmptyDeckException> {
            deck.draw()
        }
    }
}
