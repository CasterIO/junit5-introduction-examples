package de.mannodermaus.example.common.test.composition.interfaces

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

// This annotation will be inherited by all class that implement this interface.
@Tag("parser")
interface ParseableFromStringContract<out T> {

    fun createStringValues(): Stream<String>
    fun convertToObject(value: String): T

    @TestFactory
    fun parseFromString(): Stream<DynamicTest> {
        return createStringValues()
                .map { value ->
                    dynamicTest("Parse object from '$value'") {
                        val converted = convertToObject(value)
                        assertEquals(value, converted.toString())
                    }
                }
    }
}
