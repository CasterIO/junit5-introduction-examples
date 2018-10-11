package de.mannodermaus.example.common.test.conditional

import de.mannodermaus.example.common.Rank
import de.mannodermaus.example.common.Suit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfSystemProperty
import org.junit.jupiter.api.condition.EnabledIfSystemProperty

/**
 * Passing the system property from IntelliJ IDEA:
 * 1) Open the Run Configuration that executes the tests
 * 2) In the field 'VM Options', add "-Dsome.property=true"
 * 3) Click 'OK'
 *
 * Passing the system property from Gradle:
 * 1) Use the build.gradle file, or a -D parameter
 */
class SystemPropertyTests {

    @Test
    @EnabledIfSystemProperty(named = "config.value", matches = "true")
    fun onlyRunIfPropertyIsSet() {
        // Check that a Jack always has value 10
        assertTrue(Rank.Jack.value(0) == 10)
        println("System property is set")
    }

    @Test
    @DisabledIfSystemProperty(named = "config.value", matches = "true")
    fun onlyRunIfPropertyIsNotSet() {
        // Check that the symbol for the Suit of Spades is correct
        assertTrue(Suit.Spades.toString() == "♠")
        println("System property is not set")
    }
}
