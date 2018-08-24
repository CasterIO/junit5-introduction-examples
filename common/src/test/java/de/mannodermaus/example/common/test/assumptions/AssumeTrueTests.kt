package de.mannodermaus.example.common.test.assumptions

import de.mannodermaus.example.common.ALL_CARDS
import de.mannodermaus.example.common.Suit
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AssumeTrueTests {

    @Test
    @DisplayName("Check that a normal deck of 52 cards has exactly 13 ♥")
    fun checkNumberOfHeartsCardsIfNormalDeckIsGiven() {
        // When the default set of cards has 52 items...
        Assumptions.assumeTrue(ALL_CARDS.size == 52)

        // ...expect to have 13 ♥ cards
        if (ALL_CARDS.filter { it.suit == Suit.Hearts }.count() != 13) {
            throw AssertionError()
        }
    }
}
