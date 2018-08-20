package de.mannodermaus.example.test.conditional

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIf
import org.junit.jupiter.api.condition.EnabledIf

class ScriptTests {

    @EnabledIf("4 * 5 == 20")
    @Test
    fun runIfJavascriptExpressionIsValid() {
        println("Static Expression is valid")
    }

    @DisabledIf("Math.random() < 0.5")
    @Test
    fun onlyRun50PercentOfTheTime() {
        println("Executed 50% test")
    }

    @EnabledIf(
            value = [
                "load('nashorn:mozilla_compat.js')",
                "var today = java.time.LocalDate.now()",
                "var yesterday = today.minusDays(1)",
                "yesterday.isBefore(today)"
            ])
    @Test
    fun complexScript() {
        println("Javascript expression with multiple lines was evaluated to determine execution of this test")
    }
}
