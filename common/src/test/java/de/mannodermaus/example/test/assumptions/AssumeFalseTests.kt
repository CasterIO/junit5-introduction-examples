package de.mannodermaus.example.test.assumptions

import de.mannodermaus.example.ALL_CARDS
import de.mannodermaus.example.Rank
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AssumeFalseTests {

    @Test
    @DisplayName("When there aren't 4 Aces in the deck, then it shouldn't be 52 cards total")
    fun whenThereArent4AcesInTheDefaultDeckThenItShouldntBe52CardsTotal() {
        // When there aren't exactly four aces...
        Assumptions.assumeFalse(ALL_CARDS.filter { it.rank == Rank.Ace }.count() == 4)

        // ...assume that this is an odd deck
        if (ALL_CARDS.size == 52) {
            throw AssertionError()
        }
    }
}
