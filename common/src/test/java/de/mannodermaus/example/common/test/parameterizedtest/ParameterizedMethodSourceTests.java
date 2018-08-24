package de.mannodermaus.example.common.test.parameterizedtest;

import de.mannodermaus.example.common.Card;
import de.mannodermaus.example.common.Rank;
import de.mannodermaus.example.common.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedMethodSourceTests {

    // Other valid return types for these @MethodSource methods:
    // Stream<Arguments>
    // Any other Iterable<Arguments>
    // Arguments[]
    private static List<Arguments> testData() {
        return Arrays.asList(
                Arguments.of("2", "♦", 2),
                Arguments.of("7", "♣", 7),
                Arguments.of("Q", "♥", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    @DisplayName("Parsing Cards from a Rank and Suit works, and their value is as expected")
    void parsingCardsFromRankAndSuitWorksAndYieldsExpectedValue(String rankSymbol, String suitSymbol, int expectedValue) {
        Rank rank = Rank.Companion.parse(rankSymbol);
        Suit suit = Suit.Companion.parse(suitSymbol);
        new Card(rank, suit);
        assertEquals(expectedValue, rank.value(0));
    }
}
