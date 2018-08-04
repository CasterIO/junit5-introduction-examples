package io.caster.example.test.lifecycle

import io.caster.example.ALL_CARDS
import io.caster.example.Card
import io.caster.example.Deck
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertFalse

class LifecycleKotlinTests {

    companion object {

        lateinit var deck: Deck

        @BeforeAll
        @JvmStatic
        fun onBeforeAll() {
            println("@BeforeAll: Initialize shared state between tests")
            deck = Deck(ALL_CARDS)
        }

        @AfterAll
        @JvmStatic
        fun onAfterAll() {
            println("@AfterAll: Teardown shared state between tests")
        }
    }

    lateinit var card: Card

    @BeforeEach
    fun onBeforeEach() {
        println("    @BeforeEach: Draw a card from the deck.")
        card = deck.draw()
    }

    @AfterEach
    fun onAfterEach() {
        println("    @AfterEach: Remaining cards in the deck: ${deck.size}")
    }

    @Test
    fun firstTest() {
        println("        Executing the first test with: $card")
        assertFalse(card in deck)
    }

    @Test
    fun secondTest() {
        println("        Executing the second test with: $card")
        assertFalse(card in deck)
    }
}
