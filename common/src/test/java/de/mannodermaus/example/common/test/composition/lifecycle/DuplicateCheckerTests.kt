package de.mannodermaus.example.common.test.composition.lifecycle

import de.mannodermaus.example.common.ALL_CARDS
import de.mannodermaus.example.common.Card
import de.mannodermaus.example.common.Deck
import org.junit.jupiter.api.Assertions.assertTrue

class DuplicateCheckerTests : DrawEntireDeck {

    private val drawnCards = mutableListOf<Card>()

    override val deck: Deck = Deck(ALL_CARDS)

    override fun assertDrawnCard(card: Card) {
        assertTrue(card !in drawnCards)
        drawnCards += card
    }
}
