package de.mannodermaus.example.test.repeatedtest

import de.mannodermaus.example.Rank
import de.mannodermaus.example.TARGET_SUM
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo

class RepeatedTests {

    @RepeatedTest(
            value = TARGET_SUM,
            name = "when current hand score is {currentRepetition}, and Blackjack is {totalRepetitions}")
    fun theValueOfAnAceDependsOnTheCurrentScore(repetitionInfo: RepetitionInfo) {
        // This will iterate from 1 to TARGET_SUM in subsequent iterations -
        // there is also "totalRepetitions", which is always TARGET_SUM.
        val currentScore = repetitionInfo.currentRepetition
        val aceValue = Rank.Ace.value(currentScore)

        when (currentScore) {
            in 1..10 -> assertTrue(aceValue == 11)
            else -> assertTrue(aceValue == 1)
        }
    }
}
