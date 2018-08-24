@file:Suppress("SimplifyBooleanWithConstants")

package de.mannodermaus.example.common.test.conditional

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnJre
import org.junit.jupiter.api.condition.EnabledOnJre
import org.junit.jupiter.api.condition.JRE.JAVA_8

class JreTests {

    @Test
    @EnabledOnJre(JAVA_8)
    fun onlyRunThisOnJava8() {
        assertTrue(2 + 2 == 4)
    }

    @Test
    @DisabledOnJre(JAVA_8)
    fun doNotRunThisOnJava8() {
        assertTrue(2 + 2 == 4)
    }
}
