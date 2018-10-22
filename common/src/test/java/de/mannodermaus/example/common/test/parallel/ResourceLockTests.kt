package de.mannodermaus.example.common.test.parallel

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import org.junit.jupiter.api.parallel.ResourceAccessMode.READ
import org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE
import org.junit.jupiter.api.parallel.ResourceLock
import java.util.*

@Execution(CONCURRENT)
@TestInstance(PER_CLASS)
class ResourceLockTests {

    // Copy of the original System.getProperties(),
    // used to prevent mutable, shared state after these tests have executed.
    private lateinit var originalProperties: Properties

    @BeforeEach
    fun beforeEach() {
        // Save the current System properties,
        // because the tests in this class will modify them
        originalProperties = Properties()
        originalProperties.putAll(System.getProperties())
    }

    @AfterEach
    fun afterEach() {
        // Restore the original System properties
        System.setProperties(originalProperties)
    }

    @Test
    @ResourceLock("System Properties", mode = READ)
    fun checkThatPropertyIsNullByDefault() {
        assertNull(System.getProperty("custom.property"))
    }

    @Test
    @ResourceLock("System Properties", mode = READ_WRITE)
    fun checkThatPropertyIsSetCorrectlyToHello() {
        System.setProperty("custom.property", "hello")
        assertEquals("hello", System.getProperty("custom.property"))
    }

    @Test
    @ResourceLock("System Properties", mode = READ_WRITE)
    fun checkThatPropertyIsSetCorrectlyToWorld() {
        System.setProperty("custom.property", "world")
        assertEquals("world", System.getProperty("custom.property"))
    }
}
