package io.caster.example.test

import io.caster.example.Card
import io.caster.example.Deck
import io.caster.example.Rank.Ace
import io.caster.example.Rank.Num
import io.caster.example.Suit.Hearts
import io.caster.example.Suit.Spades
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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
}
