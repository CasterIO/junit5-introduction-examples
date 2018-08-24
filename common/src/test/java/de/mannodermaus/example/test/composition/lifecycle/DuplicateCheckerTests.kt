package de.mannodermaus.example.test.composition.lifecycle

import de.mannodermaus.example.ALL_CARDS
import de.mannodermaus.example.Card
import de.mannodermaus.example.Deck
import org.junit.jupiter.api.Assertions.assertTrue

class DuplicateCheckerTests : DrawEntireDeck {

    private val drawnCards = mutableListOf<Card>()

    override val deck: Deck = Deck(ALL_CARDS)

    override fun assertDrawnCard(card: Card) {
        assertTrue(card !in drawnCards)
        drawnCards += card
    }
}
