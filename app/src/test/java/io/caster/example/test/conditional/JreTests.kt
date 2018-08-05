@file:Suppress("SimplifyBooleanWithConstants")

package io.caster.example.test.conditional

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnJre
import org.junit.jupiter.api.condition.EnabledOnJre
import org.junit.jupiter.api.condition.JRE.JAVA_8

class JreTests {

    @EnabledOnJre(JAVA_8)
    @Test
    fun onlyRunThisOnJava8() {
        assertTrue(2 + 2 == 4)
    }

    @DisabledOnJre(JAVA_8)
    @Test
    fun doNotRunThisOnJava8() {
        assertTrue(2 + 2 == 4)
    }
}
