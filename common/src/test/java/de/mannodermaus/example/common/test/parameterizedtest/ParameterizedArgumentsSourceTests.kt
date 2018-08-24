package de.mannodermaus.example.common.test.parameterizedtest

import de.mannodermaus.example.common.Rank
import de.mannodermaus.example.common.TARGET_SUM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.IntStream
import java.util.stream.Stream

class ParameterizedArgumentsSourceTests {

    @ParameterizedTest(name = "Given a score of {0}, the value of an Ace is {1}")
    @ArgumentsSource(CustomArgumentsSource::class)
    fun test(score: Int, aceValue: Int) {
        when {
            score < 11 -> assertEquals(11, aceValue)
            else -> assertEquals(1, aceValue)
        }
    }
}

class CustomArgumentsSource : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext): Stream<out Arguments> {
        // From 0 to 21, programmatically map the potential value of an Ace, based on a base sum
        return IntStream.range(0, TARGET_SUM)
                .mapToObj { i -> i to Rank.Ace.value(i) }
                .map { Arguments.of(it.first, it.second) }
    }
}
