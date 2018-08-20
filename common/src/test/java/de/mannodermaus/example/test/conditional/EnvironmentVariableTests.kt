package de.mannodermaus.example.test.conditional

import de.mannodermaus.example.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EnvironmentVariableTests {

    @EnabledIfEnvironmentVariable(named = "MY_ENV_VAR", matches = "true")
    @Test
    fun onlyRunIfTheEnvironmentVariableIsSet() {
        // Check that an empty Hand has score "0"
        val hand = Hand()
        assertTrue(hand.sum() == 0)
    }
}
