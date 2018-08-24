package de.mannodermaus.example.common.test.conditional

import de.mannodermaus.example.common.Card
import de.mannodermaus.example.common.Hand
import de.mannodermaus.example.common.Rank
import de.mannodermaus.example.common.Suit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable

class EnvironmentVariableTests {

    @Test
    @EnabledIfEnvironmentVariable(named = "MY_ENV_VAR", matches = "true")
    fun onlyRunIfTheEnvironmentVariableIsSet() {
        // Check that an empty Hand has score "0"
        val hand = Hand()
        assertTrue(hand.sum == 0)
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "MY_ENV_VAR", matches = "true")
    fun disableIfTheEnvironmentVariableIsSet() {
        // Check that a Hand containing an Ace of Spades has score "11"
        val hand = Hand().apply {
            add(Card(Rank.Ace, Suit.Spades))
        }

        assertTrue(hand.sum == 11)
    }
}
