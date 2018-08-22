package de.mannodermaus.example.test.tags;

import de.mannodermaus.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static de.mannodermaus.example.ConstantsKt.ALL_CARDS;
import static de.mannodermaus.example.ConstantsKt.TARGET_SUM;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TagTests {

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

    @Test
    @Tag("slow")
    void slowTest() {
        // Create a deck with 250k Aces of Spades & draw them one by one
        List<Card> cards = IntStream.range(1, 250_000)
                .mapToObj(i -> new Card(Rank.Ace.INSTANCE, Suit.Spades))
                .collect(toList());

        Deck deck = new Deck(cards);

        for (int i = 0; i < cards.size(); i++) {
            Card card = deck.draw();
            Assertions.assertAll(
                    () -> assertSame(card.getRank(), Rank.Ace.INSTANCE),
                    () -> assertSame(card.getSuit(), Suit.Spades)
            );
        }
     }
}
