package de.mannodermaus.example.common.test.parameterizedtest

import de.mannodermaus.example.common.Card
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class ParameterizedCsvFileSourceTests {

    @ParameterizedTest
    @CsvFileSource(
            resources = ["/data/csv_file_data.csv"],
            encoding = "UTF-8",
            lineSeparator = "\n",
            delimiter = ',',
            numLinesToSkip = 0
    )
    fun parseCardsAndCheckTheirUsualValue(str: String, value: Int) {
        val card = Card.parse(str)
        assertEquals(value, card.rank.value(0))
    }
}
