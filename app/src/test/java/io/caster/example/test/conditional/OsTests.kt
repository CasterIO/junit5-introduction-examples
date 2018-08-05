package io.caster.example.test.conditional

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS.*

@DisabledOnOs(SOLARIS)
class OsTests {

    @EnabledOnOs(MAC)
    @Test
    fun onlyRunThisOnMac() {
        assertTrue(System.getProperty("user.home").startsWith("/Users"))
    }

    @EnabledOnOs(WINDOWS)
    @Test
    fun onlyRunThisOnWindows() {
        assertTrue(System.getenv("SystemRoot").contains(":\\Windows"))
    }
}
