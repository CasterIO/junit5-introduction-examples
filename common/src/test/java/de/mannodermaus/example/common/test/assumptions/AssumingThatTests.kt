package de.mannodermaus.example.common.test.assumptions

import de.mannodermaus.example.common.Rank
import de.mannodermaus.example.common.TARGET_SUM
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class AssumingThatTests {

    @Test
    @DisplayName("Given a random score between 0 and 21, check that an Ace adds 1 or 11 value")
    fun anAceCountsCorrectlyBasedOnARandomSum() {
        // For demonstration purposes, this test runs against random scores
        val score = Random().nextInt(TARGET_SUM)
        val aceValue = Rank.Ace.value(currentSum = score)

        Assumptions.assumingThat(score <= TARGET_SUM - 11) {
            println("Performing the 'is 11?' check against: $score")
            Assertions.assertTrue(aceValue == 11)
        }

        Assumptions.assumingThat(score > TARGET_SUM - 11) {
            println("Performing the 'is 1?' check against: $score")
            Assertions.assertTrue(aceValue == 1)
        }
    }
}
