package de.mannodermaus.example.common.test.composition.interfaces

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

interface ComparableContract<T> where T : Comparable<T> {
    fun value(): T
    fun smallerValue(): T
    fun equalValue(): T? = null

    @Test
    fun zeroIfComparingToSelf() {
        val value = value()
        assertEquals(0, value.compareTo(value))
    }

    @Test
    fun zeroIfComparingToEqualValue() {
        val value = value()
        val other = equalValue()
        assertEquals(0, value.compareTo(other ?: value))
    }

    @Test
    fun positiveIfComparingToSmallerValue() {
        val value = value()
        val smaller = smallerValue()
        assertTrue(value > smaller)
    }

    @Test
    fun negativeIfSmallerValueIsCompared() {
        val value = value()
        val smaller = smallerValue()
        assertTrue(smaller < value)
    }
}