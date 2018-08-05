package io.caster.example.test.tags

import io.caster.example.Card
import io.caster.example.Deck
import io.caster.example.Rank
import io.caster.example.Suit
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
