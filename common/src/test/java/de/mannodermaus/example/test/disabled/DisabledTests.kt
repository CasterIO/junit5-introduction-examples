package de.mannodermaus.example.test.disabled

import de.mannodermaus.example.Card
import de.mannodermaus.example.Rank
import de.mannodermaus.example.Suit
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DisabledTests {

    @Test
    @Disabled
    @DisplayName("Disabled, because trying to create a Number Card not in [2..10] would fail")
    fun disabledBecauseTryingToCreateANumberedCardOutsideTheAllowedBoundsWouldFail() {
        // Try to create a "13 of Hearts" card
        Card(rank = Rank.Num(number = 13), suit = Suit.Hearts)
    }
}
