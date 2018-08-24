package de.mannodermaus.example.common.test.composition.interfaces

import de.mannodermaus.example.common.Suit

class ComparableSuitTests : ComparableContract<Suit> {
    override fun value() = Suit.Hearts
    override fun smallerValue() = Suit.Diamonds
}
