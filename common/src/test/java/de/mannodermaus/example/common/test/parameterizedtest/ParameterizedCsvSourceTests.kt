package de.mannodermaus.example.common.test.parameterizedtest

import de.mannodermaus.example.common.Card
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ParameterizedCsvSourceTests {

    @ParameterizedTest
    @CsvSource(value = [
        "A♥,  2♥,  5♣,  18",
        "10♥, 6♥,  A♣,  17",
        "A♥,  A♦,  9♥,  21"
    ])
    fun theSumOfThreeCardsIsCorrectlyPredicted(str1: String, str2: String, str3: String, expectedSum: Int) {
        val card1 = Card.parse(str1)
        val card2 = Card.parse(str2)
        val card3 = Card.parse(str3)
        assertEquals(expectedSum, Card.sum(card1, card2, card3))
    }
}
