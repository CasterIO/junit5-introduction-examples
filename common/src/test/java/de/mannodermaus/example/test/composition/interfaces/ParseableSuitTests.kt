package de.mannodermaus.example.test.composition.interfaces

import de.mannodermaus.example.Suit
import java.util.stream.Stream

class ParseableSuitTests : ParseableFromStringContract<Suit>, ParseableToStringContract<Suit> {
    override fun createStringValues(): Stream<String> = Stream.of(
            "♥",
            "♣",
            "♦",
            "♠"
    )

    override fun convertToObject(value: String) = Suit.parse(value)

    override fun createObjectValues(): Stream<Suit> = Stream.of(
            Suit.Hearts,
            Suit.Clubs,
            Suit.Diamonds,
            Suit.Spades
    )

    override fun convertToString(value: Suit) = value.toString()
}