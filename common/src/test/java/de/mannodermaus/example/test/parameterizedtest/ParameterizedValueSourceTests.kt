package de.mannodermaus.example.test.parameterizedtest

import de.mannodermaus.example.Card
import de.mannodermaus.example.Rank
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParameterizedValueSourceTests {

    @ParameterizedTest(name = "Parsing {0} returns a totally valid Card")
    @ValueSource(strings = ["2♥", "9♦", "10♥", "J♣", "Q♣", "K♦", "A♥"])
    fun parsingCardsFromStringWithValidValues(string: String) {
        assertNotNull(Card.parse(string))
    }

    @ParameterizedTest(name = "There is no numbered card with value {0}")
    @ValueSource(ints = [1, 14, 1337])
    fun creatingNumberRanksOutsideOfTheAllowedRange(value: Int) {
        assertThrows<IllegalArgumentException> {
            Rank.Num(value)
        }
    }

    // @ValueSource can also take:
    // short[]
    // byte[]
    // long[]
    // float[]
    // double[]
    // char[]
    // Class<?>[]
}
