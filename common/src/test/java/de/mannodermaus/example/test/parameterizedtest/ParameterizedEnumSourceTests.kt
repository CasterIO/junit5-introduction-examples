package de.mannodermaus.example.test.parameterizedtest

import de.mannodermaus.example.Suit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class ParameterizedEnumSourceTests {

    @ParameterizedTest
    @EnumSource(Suit::class)
    fun everySuitSymbolConsistsOfExactlyOneCharacter(suit: Suit) {
        assertTrue(suit.toString().length == 1)
    }
}
