package de.mannodermaus.example.common.test.composition.interfaces

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

// This annotation will be inherited by all class that implement this interface.
@Tag("parser")
interface ParseableToStringContract<T> {

    fun createObjectValues(): Stream<T>
    fun convertToString(value: T): String

    @TestFactory
    fun parseToString(): Stream<DynamicTest> {
        return createObjectValues()
                .map { obj ->
                    dynamicTest("Parse '$obj' to string") {
                        val converted = convertToString(obj)
                        assertEquals(obj.toString(), converted)
                    }
                }
    }
}
