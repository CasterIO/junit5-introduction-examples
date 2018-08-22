package de.mannodermaus.example.test.assertions

import de.mannodermaus.example.Card
import de.mannodermaus.example.Deck
import de.mannodermaus.example.Rank
import de.mannodermaus.example.Suit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AssertAllTests {

    @Test
    @DisplayName("contains() works for multiple cards actually in a Deck")
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
