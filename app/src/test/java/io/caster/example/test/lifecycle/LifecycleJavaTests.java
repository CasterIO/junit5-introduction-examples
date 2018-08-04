package io.caster.example.test.lifecycle;

import io.caster.example.Card;
import io.caster.example.Deck;
import org.junit.jupiter.api.*;

import static io.caster.example.ConstantsKt.ALL_CARDS;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LifecycleJavaTests {

    private static Deck deck;

    private Card card;

    @BeforeAll
    private static void onBeforeAll() {
        System.out.println("@BeforeAll: Initialize shared state between tests");
        deck = new Deck(ALL_CARDS);
    }

    @AfterAll
    private static void onAfterAll() {
        System.out.println("@AfterAll: Teardown shared state between tests");
    }

    @BeforeEach
    private void onBeforeEach() {
        System.out.println("    @BeforeEach: Draw a card from the deck.");
        card = deck.draw();
    }

    @AfterEach
    private void onAfterEach() {
        System.out.println("    @AfterEach: Remaining cards in the deck: " + deck.getSize());
    }

    @Test
    void firstTest() {
        System.out.println("        Executing the first test with: " + card);
        assertFalse(deck.contains(card));
    }

    @Test
    void secondTest() {
        System.out.println("        Executing the second test with: " + card);
        assertFalse(deck.contains(card));
    }
}
