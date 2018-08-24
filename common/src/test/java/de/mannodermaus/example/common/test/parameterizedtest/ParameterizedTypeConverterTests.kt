package de.mannodermaus.example.common.test.parameterizedtest

import de.mannodermaus.example.common.Rank
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.converter.SimpleArgumentConverter
import org.junit.jupiter.params.provider.CsvSource

class ParameterizedTypeConverterTests {

    @ParameterizedTest
    @CsvSource(value = [
        "2,  Num,   2",
        "3,  Num,   3",
        "4,  Num,   4",
        "5,  Num,   5",
        "6,  Num,   6",
        "7,  Num,   7",
        "8,  Num,   8",
        "9,  Num,   9",
        "10, Num,   10",
        "J,  Jack,  10",
        "Q,  Queen, 10",
        "K,  King,  10",
        "A,  Ace,   11"
    ])
    fun verifyThatParsedRanksAreAssignedTheCorrectClassAndValue(
            @ConvertWith(RankConverter::class)
            rank: Rank,
            expectedClassName: String,
            expectedValue: Int) {
        assertEquals(expectedClassName, rank.javaClass.simpleName)
        assertEquals(expectedValue, rank.value(0))
    }
}

class RankConverter : SimpleArgumentConverter() {
    override fun convert(source: Any, targetType: Class<*>) = Rank.parse(source.toString())
}
