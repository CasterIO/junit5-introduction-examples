package de.mannodermaus.example.common.test.dynamictest

import de.mannodermaus.example.common.Card
import de.mannodermaus.example.common.Rank
import de.mannodermaus.example.common.Suit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.IntStream
import java.util.stream.Stream

class DynamicTests {

    // Alternatively, a @TestFactory can also return:
    // * Iterator<DynamicNode>
    // * Iterable<DynamicNode>
    // * DynamicNode[]
    @TestFactory
    fun dynamicTestsGeneratedAtRuntime(): Stream<DynamicTest> {
        // Create test methods from a text file.
        // Each entry in that file not starting with a '#' sign is parsed into 1 case.
        // We expect 3 comma-separated values per case, which are mapped
        // to the dynamicTest() factory method.
        return linesOfResourceFile("data/dynamic_test_data.txt")
                .map { line -> line.split(',') }
                .filter { it.size == 3 }
                .map { (testName, cardValue, cardName) ->
                    dynamicTest(testName) {
                        // Body of the programmatically generated test method
                        val card = Card.parse(cardValue.trim())
                        assertNotNull(card)
                        assertEquals(cardName.trim(), card.toLongString())
                    }
                }
    }

    @TestFactory
    fun dynamicTestsWithLogicalGroupingsGeneratedAtRuntime(): Stream<DynamicNode> {
        // DynamicContainer and DynamicTest objects can be created from streams,
        // and they allow for logical nesting of programmatically generated tests.
        return IntStream.range(0, 4)
                .mapToObj { i -> Suit.values()[i] }
                .map { suit ->
                    dynamicContainer("Dynamic Tests with ${suit.toLongString()}",
                            IntStream.range(2, 10)
                                    .mapToObj { number ->
                                        dynamicTest("$number$suit is a valid Card") {
                                            val card = Card(rank = Rank.Num(number), suit = suit)
                                            assertEquals("$number$suit", card.toString())
                                        }
                                    }
                    )
                }
    }

    /* Private helpers */

    private fun linesOfResourceFile(path: String): Stream<String> {
        val resource = javaClass.classLoader.getResource(path)
        assertNotNull(resource)

        return Files.lines(Paths.get(resource.toURI()))
                .filter { !it.startsWith('#') }
    }
}
