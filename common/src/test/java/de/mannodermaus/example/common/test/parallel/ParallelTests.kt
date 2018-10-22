package de.mannodermaus.example.common.test.parallel

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT

@TestInstance(PER_CLASS)
class ParallelTests {

    private var start: Long = 0

    @BeforeAll
    fun beforeAll() {
        start = System.nanoTime()
        println("Running on ${Runtime.getRuntime().availableProcessors()} cores")
    }

    @AfterAll
    fun afterAll() {
        val end = System.nanoTime()
        println("This took ${(end - start) / 1_000_000} ms")
    }

    // Switch to SAME_THREAD with parallel execution enabled for the default behavior.
    @Execution(CONCURRENT)
    @TestFactory
    fun slowTests() = LongRange(5, 20)
            .map { num ->
                dynamicTest("Slow Test $num") {
                    println("Executing Test $num on thread ${Thread.currentThread()}")
                    Thread.sleep(num * 1_000)
                }
            }
}
