package io.caster.example.test.conditional

import io.caster.example.Rank
import io.caster.example.Suit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
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
@TestInstance(PER_CLASS)
class SystemPropertyTests {

    @EnabledIfSystemProperty(named = "some.property", matches = "true")
    @Test
    fun onlyRunIfPropertyIsSet() {
        // Check that a Jack always has value 10
        assertTrue(Rank.Jack.value(0) == 10)
        println("System property is set")
    }

    @DisabledIfSystemProperty(named = "some.property", matches = "true")
    @Test
    fun onlyRunIfPropertyIsNotSet() {
        // Check that the symbol for the Suit of Spades is correct
        assertTrue(Suit.Spades.toString() == "♠")
        println("System property is not set")
    }
}
