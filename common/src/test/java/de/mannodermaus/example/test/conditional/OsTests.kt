package de.mannodermaus.example.test.conditional

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS.MAC
import org.junit.jupiter.api.condition.OS.WINDOWS
import java.util.*

class OsTests {

    @Test
    @EnabledOnOs(MAC)
    fun onlyRunThisOnMac() {
        assertTrue(System.getProperty("user.home").startsWith("/Users"))
    }

    @Test
    @DisabledOnOs(MAC)
    fun neverRunThisOnMac() {
        val osName = System.getProperty("os.name").toLowerCase(Locale.ROOT)
        assertFalse("darwin" in osName)
        assertFalse("mac" in osName)
    }

    @Test
    @EnabledOnOs(WINDOWS)
    fun onlyRunThisOnWindows() {
        assertTrue(System.getenv("SystemRoot").contains(":\\Windows"))
    }

    @Test
    @DisabledOnOs(WINDOWS)
    fun neverRunThisOnWindows() {
        Runtime.getRuntime().exec("clear")
    }
}
