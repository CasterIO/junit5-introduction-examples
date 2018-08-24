package de.mannodermaus.example.common.test.composition.interfaces

import de.mannodermaus.example.common.Card
import de.mannodermaus.example.common.Rank
import de.mannodermaus.example.common.Suit
import java.util.stream.Stream

class ParseableCardTests : ParseableFromStringContract<Card>, ParseableToStringContract<Card> {

    override fun createStringValues(): Stream<String> = Stream.of(
            "2♦",
            "3♦",
            "4♦",
            "5♦",
            "6♦",
            "7♦",
            "8♦",
            "9♦",
            "10♦",
            "J♦",
            "Q♦",
            "K♦",
            "A♦"
    )

    override fun convertToObject(value: String) = Card.parse(value)

    override fun createObjectValues(): Stream<Card> = Stream.of(
            Card(Rank.Num(2), Suit.Diamonds),
            Card(Rank.Num(3), Suit.Diamonds),
            Card(Rank.Num(4), Suit.Diamonds),
            Card(Rank.Num(5), Suit.Diamonds),
            Card(Rank.Num(6), Suit.Diamonds),
            Card(Rank.Num(7), Suit.Diamonds),
            Card(Rank.Num(8), Suit.Diamonds),
            Card(Rank.Num(9), Suit.Diamonds),
            Card(Rank.Num(10), Suit.Diamonds),
            Card(Rank.Jack, Suit.Diamonds),
            Card(Rank.Queen, Suit.Diamonds),
            Card(Rank.King, Suit.Diamonds),
            Card(Rank.Ace, Suit.Diamonds)
    )

    override fun convertToString(value: Card) = value.toString()
}