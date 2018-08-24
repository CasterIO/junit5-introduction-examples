package de.mannodermaus.example.android.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class JUnitJupiterInstrumentationTests {

    @Test
    fun testRunningWithTheJUnitPlatform() {
        Assertions.assertTrue(2 + 2 == 4)
    }

    @Disabled
    @Test
    fun anotherDisabledJUnitJupiterTest() {
        fail { "You should've never removed that annotation." }
    }
}
