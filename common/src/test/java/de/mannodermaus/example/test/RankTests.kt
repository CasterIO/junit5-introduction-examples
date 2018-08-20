package de.mannodermaus.example.test

import de.mannodermaus.example.Card
import de.mannodermaus.example.Rank
import de.mannodermaus.example.Rank.Num
import de.mannodermaus.example.Suit.Hearts
import de.mannodermaus.example.TARGET_SUM
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumingThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.util.*

@TestInstance(PER_CLASS)
class RankTests {

    @Test
    fun anAceCountsOnlyAs1WhenItWouldOtherwiseCauseABust() {
        // Assume that a player is 6 points apart from a Blackjack
        val closeToBustSum = TARGET_SUM - 6
        assertTrue(Rank.Ace.value(currentSum = closeToBustSum) == 1)
    }

    @Test
    fun anAceCountsCorrectlyBasedOnARandomSum() {
        // For demonstration purposes, this test runs against random scores
        val randomSum = Random().nextInt(TARGET_SUM)

        assumingThat(randomSum <= TARGET_SUM - 11) {
            println("Performing the 'is 11?' check against: $randomSum")
            assertTrue(Rank.Ace.value(currentSum = randomSum) == 11)
        }

        assumingThat(randomSum > TARGET_SUM - 11) {
            println("Performing the 'is 1?' check against: $randomSum")
            assertTrue(Rank.Ace.value(currentSum = randomSum) == 1)
        }
    }

    @Disabled
    @Test
    @DisplayName("Disabled, because trying to create a Number Card not in [2..10] would fail")
    fun disabledBecauseTryingToCreateANumberedCardOutsideTheAllowedBoundsWouldFail() {
        // Try to create a "13 of Hearts" card
        Card(rank = Num(number = 13), suit = Hearts)
    }
}
