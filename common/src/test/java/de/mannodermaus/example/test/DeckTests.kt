package de.mannodermaus.example.test

import de.mannodermaus.example.Card
import de.mannodermaus.example.Deck
import de.mannodermaus.example.EmptyDeckException
import de.mannodermaus.example.Rank.*
import de.mannodermaus.example.Suit.Hearts
import de.mannodermaus.example.Suit.Spades
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("Integrity Tests for Deck objects")
class DeckTests {

    @Test
    @DisplayName("When no cards are added to a Deck, it is empty")
    fun isEmptyReturnsTrueForDecksWithNoCardsAdded() {
        val deck = Deck(cards = emptyList())
        assertTrue(deck.isEmpty())
    }

    @Test
    @DisplayName("A Deck doesn't contain an unrelated Card")
    fun cardThatIsNotPartOfADeckIsNotReportedAsBeingContainedInTheDeck() {
        val containedCard = Card(rank = Ace, suit = Spades)
        val unrelatedCard = Card(rank = Num(7), suit = Hearts)
        val deck = Deck(listOf(containedCard))
        assertFalse(unrelatedCard in deck)
    }

    @Test
    @DisplayName("Drawing from an empty deck throws an Exception")
    fun drawingFromEmptyDeckThrowsException() {
        val deck = Deck(cards = emptyList())
        assertThrows<EmptyDeckException> {
            deck.draw()
        }
    }

    @Test
    @DisplayName("A Deck properly reports cards that it actually contains")
    fun cardsThatAreContainedInTheDeckAreCorrectlyReported() {
        val card1 = Card(rank = Ace, suit = Spades)
        val card2 = Card(rank = Queen, suit = Hearts)
        val deck = Deck(listOf(card1, card2))
        assertAll("Check that all cards are contained in the deck",
                { assertTrue(card1 in deck) },
                { assertTrue(card2 in deck) }
        )
    }
}
