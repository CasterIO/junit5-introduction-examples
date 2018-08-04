package io.caster.example.test

import io.caster.example.Rank
import io.caster.example.TARGET_SUM
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Assumptions.assumingThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.time.LocalTime
import java.util.*

@TestInstance(PER_CLASS)
class RankTests {

    @Test
    fun anAceCountsOnlyAs1WhenItWouldOtherwiseCauseABust() {
        // For demonstration purposes, this test only runs every 30 seconds
        assumeTrue(LocalTime.now().second >= 30) {
            "This test only runs in the second half of each minute!"
        }

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
}
