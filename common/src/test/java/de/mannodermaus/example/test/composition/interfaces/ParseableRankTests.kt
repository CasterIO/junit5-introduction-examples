package de.mannodermaus.example.test.composition.interfaces

import de.mannodermaus.example.Rank
import java.util.stream.Stream

class ParseableRankTests : ParseableFromStringContract<Rank>, ParseableToStringContract<Rank> {
    override fun createStringValues(): Stream<String> = Stream.of(
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "J",
            "Q",
            "K",
            "A"
    )

    override fun convertToObject(value: String) = Rank.parse(value)

    override fun createObjectValues(): Stream<Rank> = Stream.of(
            Rank.Num(2),
            Rank.Num(3),
            Rank.Num(4),
            Rank.Num(5),
            Rank.Num(6),
            Rank.Num(7),
            Rank.Num(8),
            Rank.Num(9),
            Rank.Num(10),
            Rank.Jack,
            Rank.Queen,
            Rank.King,
            Rank.Ace
    )

    override fun convertToString(value: Rank) = value.toString()
}