package de.mannodermaus.example.common.test.composition.interfaces

import de.mannodermaus.example.common.Rank

class ComparableRankTests : ComparableContract<Rank> {
    // Reference value
    override fun value() = Rank.Num(10)

    // Definitely smaller than a 10
    override fun smallerValue() = Rank.Num(3)

    // Jack also has a value of 10
    override fun equalValue() = Rank.Jack

}