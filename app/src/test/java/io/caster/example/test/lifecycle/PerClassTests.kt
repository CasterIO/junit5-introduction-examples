package io.caster.example.test.lifecycle

import io.caster.example.ALL_CARDS
import io.caster.example.Card
import io.caster.example.Deck
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class PerClassTests {

    lateinit var deck: Deck
    lateinit var card: Card

    @BeforeAll
    fun onBeforeAll() {
        println("@BeforeAll: Initialize shared state between tests")
        deck = Deck(ALL_CARDS)
    }

    @AfterAll
    fun onAfterAll() {
        println("@AfterAll: Teardown shared state between tests")
    }

    @BeforeEach
    fun onBeforeEach() {
        println("    @BeforeEach: Draw a card from the deck. Test Instance: $this")
        card = deck.draw()
    }

    @AfterEach
    fun onAfterEach() {
        println("    @AfterEach: Remaining cards in the deck: ${deck.size}. Test Instance: $this")
    }

    @Test
    fun firstTest() {
        println("        Executing the first test with: $card on Test Instance: $this")
        assertFalse(card in deck)
    }

    @Test
    fun secondTest() {
        println("        Executing the second test with: $card on Test Instance: $this")
        assertFalse(card in deck)
    }
}
