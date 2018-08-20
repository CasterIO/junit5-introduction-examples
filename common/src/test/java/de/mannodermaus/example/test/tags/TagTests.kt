package de.mannodermaus.example.test.tags

import de.mannodermaus.example.Card
import de.mannodermaus.example.Deck
import de.mannodermaus.example.Rank
import de.mannodermaus.example.Suit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TagTests {
    @Test
    @Tag("slow")
    fun slowTest() {
        // Create a deck with 250k Aces of Spades & draw them again
        val cards = (1..250_000).map { Card(Rank.Ace, Suit.Spades) }
        val deck = Deck(cards)
        for (i in (1..250_000)) {
            val card = deck.draw()
            assertAll(
                    { assertTrue(card.rank == Rank.Ace) },
                    { assertTrue(card.suit == Suit.Spades) }
            )
        }
    }
}
