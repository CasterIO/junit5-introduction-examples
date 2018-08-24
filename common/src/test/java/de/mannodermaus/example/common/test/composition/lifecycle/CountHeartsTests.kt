package de.mannodermaus.example.common.test.composition.lifecycle

import de.mannodermaus.example.common.ALL_CARDS
import de.mannodermaus.example.common.Card
import de.mannodermaus.example.common.Deck
import de.mannodermaus.example.common.Suit
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals

class CountHeartsTests : DrawEntireDeck {

    private val collectedHeartsCards = mutableListOf<Card>()

    override val deck = Deck(ALL_CARDS)

    override fun assertDrawnCard(card: Card) {
        if (card.suit == Suit.Hearts) {
            collectedHeartsCards += card
        }
    }

    @AfterAll
    override fun afterAll() {
        super.afterAll()

        // Expect 13 "♥" cards in a standard deck
        assertEquals(13, collectedHeartsCards.size)
    }
}
