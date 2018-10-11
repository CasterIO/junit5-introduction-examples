package de.mannodermaus.example.common.test.conditional

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIf
import org.junit.jupiter.api.condition.EnabledIf

class ScriptTests {

    @Test
    @EnabledIf("4 * 5 == 20")
    fun runIfJavascriptExpressionIsValid() {
        println("Static Expression is valid")
    }

    @Test
    @DisabledIf("Math.random() < 0.5")
    fun onlyRun50PercentOfTheTime() {
        println("Executed 50% test")
    }

    @Test
    @EnabledIf(
            value = [
                "load('nashorn:mozilla_compat.js')",
                "var today = java.time.LocalDate.now()",
                "var yesterday = today.minusDays(1)",
                "yesterday.getDayOfWeek() == java.time.DayOfWeek.MONDAY"
            ])
    fun complexScript() {
        println("Javascript expression with multiple lines was evaluated to determine execution of this test")
    }
}
