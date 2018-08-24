package de.mannodermaus.example.test.composition.interfaces

import de.mannodermaus.example.Suit

class ComparableSuitTests : ComparableContract<Suit> {
    override fun value() = Suit.Hearts
    override fun smallerValue() = Suit.Diamonds
}
