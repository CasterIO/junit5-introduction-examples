package de.mannodermaus.example.common.test.displayname

import de.mannodermaus.example.common.*
import org.junit.jupiter.api.*

@DisplayName("Unit Tests with @DisplayName annotation")
class DisplayNameTests {

    @Test
    @DisplayName("Drawing from an empty deck throws an Exception")
    fun drawingFromEmptyDeckThrowsException() {
        val deck = Deck(cards = emptyList())
        assertThrows<EmptyDeckException> {
            deck.draw()
        }
    }

    @Test
    @DisplayName("A Deck properly reports cards that it actually contains \uD83D\uDC96")
    fun cardsThatAreContainedInTheDeckAreCorrectlyReported() {
        val card1 = Card(rank = Rank.Ace, suit = Suit.Spades)
        val card2 = Card(rank = Rank.Queen, suit = Suit.Hearts)
        val deck = Deck(listOf(card1, card2))
        assertAll("Check that all cards are contained in the deck",
                { Assertions.assertTrue(card1 in deck) },
                { Assertions.assertTrue(card2 in deck) }
        )
    }
}
