package io.caster.example

// Generate a standard deck of playing cards:
// For each suit, add the number values from 2 to 10,
// as well as the pictured values.
private val ALL_CARDS = Suit.values()
        .flatMap { suit ->
            mutableListOf<Card>().apply {
                addAll((2..10).map { Card(rank = Rank.Num(it), suit = suit) })
                add(Card(rank = Rank.Jack, suit = suit))
                add(Card(rank = Rank.Queen, suit = suit))
                add(Card(rank = Rank.King, suit = suit))
                add(Card(rank = Rank.Ace, suit = suit))
            }
        }

/**
 * Main method
 */
fun main(args: Array<String>) {
    val deck = Deck(ALL_CARDS)
    deck.shuffle()
    println(deck.draw())
    println(deck)
    println(ALL_CARDS)
}
