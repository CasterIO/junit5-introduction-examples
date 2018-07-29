package io.caster.example

/**
 * Represents a deck of cards, which can be shuffled and drawn from.
 * Won't mutate the given list of cards, instead holds a mutable copy of its data.
 */
class Deck(cards: List<Card>) {

    private var cards = cards.toMutableList()

    val size get() = cards.size

    fun isEmpty() = cards.isEmpty()

    fun shuffle() {
        cards.shuffle()
    }

    fun draw() = if (isEmpty()) {
        throw EmptyDeckException()
    } else {
        cards.removeAt(0)
    }

    override fun toString() = "Deck($size cards left)"
}

/**
 * Represents the "hand" of a player, containing stacked cards.
 */
class Hand {

    private var _cards = mutableListOf<Card>()
    val cards get() = _cards.toList()

    /**
     * Adds the given card to this hand,
     * without any checks around the resulting sum of these cards.
     */
    fun add(card: Card) = _cards.add(card)

    fun sum() = _cards
            .map { card -> card.rank }
            .map { rank -> rank.value }
            .sum()

    override fun toString() = "Hand(cards=$cards)"
}

/**
 * Represents a playing card.
 * Each card has a Rank and a Suit.
 */
data class Card(val rank: Rank, val suit: Suit) {
    override fun toString() = "$rank$suit"
}

/**
 * Represents the different suits for cards.
 */
enum class Suit(private val symbol: String) {
    Hearts("♥"),
    Clubs("♣"),
    Diamonds("♦"),
    Spades("♠");

    override fun toString() = symbol
}

/**
 * Represents the different ranks for a card.
 */
sealed class Rank(val symbol: String, val value: Int) {

    /**
     * Represents card values between "2" and "10".
     * Will throw an IllegalArgumentException for values outside that range
     */
    class Num(value: Int) : Rank(symbol = value.toString(), value = value) {
        init {
            if (value !in 2..10) {
                throw IllegalArgumentException("illegal Num: $value")
            }
        }
    }

    /* Remaining card ranks, implemented as singleton objects */

    object Jack : Rank(symbol = "J", value = 10)
    object Queen : Rank(symbol = "Q", value = 10)
    object King : Rank(symbol = "K", value = 10)
    object Ace : Rank(symbol = "A", value = 11)

    override fun toString(): String = symbol
}
