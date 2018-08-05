package io.caster.example.test.tags;

import io.caster.example.Deck;
import io.caster.example.Session;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.caster.example.ConstantsKt.ALL_CARDS;
import static io.caster.example.ConstantsKt.TARGET_SUM;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaTagTests {

    @Test
    @Tag("slow")
    @Tag("another-tag")
    void testMethodWithTwoTags() {
        try {
            // Artificial delay to mimick the behavior of a "slow" test
            Thread.sleep(2_500);
        } catch (InterruptedException ignored) {
        }

        // If you drew all the cards out of the deck, you'd probably have more than 21
        Deck deck = new Deck(ALL_CARDS);
        Session session = new Session(deck, "Player 1");

        while (!deck.isEmpty()) {
            session.makeTurn();
        }

        assertTrue(session.getScore() > TARGET_SUM);
    }
}
