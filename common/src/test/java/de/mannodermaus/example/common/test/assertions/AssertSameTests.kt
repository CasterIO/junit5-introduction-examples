package de.mannodermaus.example.common.test.assertions

import de.mannodermaus.example.common.Card
import de.mannodermaus.example.common.Rank
import de.mannodermaus.example.common.Suit
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class AssertSameTests {

    @Test
    fun anAceIsAnAceIsAnAce() {
        val aceOfSpades = Card(Rank.Ace, Suit.Spades)
        val aceOfHearts = Card(Rank.Ace, Suit.Hearts)

        assertSame(aceOfSpades.rank, aceOfHearts.rank)
    }
}