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

    override fun toString() = if (isEmpty())
        "Empty Deck"
    else
        "Deck of $size"
}

/**
 * Represents a player of the Blackjack game.
 */
typealias Player = String

/**
 * Represents the "hand" of a player, containing stacked cards.
 */
class Hand(private var _cards: MutableList<Card> = mutableListOf<Card>()) {

    val cards get() = _cards.toList()

    /**
     * Adds the given card to this hand,
     * without any checks around the resulting sum of these cards.
     */
    fun add(card: Card) = _cards.add(card)

    fun sum(): Int {
        var sum = 0
        for (card in cards) {
            sum += card.rank.value(sum)
        }
        return sum
    }

    override fun toString() = "Hand(cards=$cards)"

    operator fun plusAssign(card: Card) {
        add(card)
    }
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
open class Rank(val symbol: String) {

    /**
     * The value of a card with this rank.
     * When summing up a hand, the current sum
     * is passed to this method as well,
     * in order to allow for conditional logic
     * that determines the value of this rank.
     * The prime example is the varying value for Aces.
     */
    open fun value(currentSum: Int): Int = 0

    override fun toString(): String = symbol

    /**
     * Represents card values between "2" and "10".
     * Will throw an IllegalArgumentException for values outside that range
     */
    class Num(private val number: Int) : Rank(symbol = number.toString()) {
        override fun value(currentSum: Int) = number

        init {
            // Check boundaries
            if (number !in 2..10) {
                throw IllegalArgumentException("illegal Num: $number")
            }
        }
    }

    /**
     * Foundation for Face cards (Jack, Queen, King),
     * all using the same base value
     */
    abstract class Face(symbol: String) : Rank(symbol = symbol) {
        override fun value(currentSum: Int) = 10
    }

    object Jack : Face(symbol = "J")
    object Queen : Face(symbol = "Q")
    object King : Face(symbol = "K")

    /**
     * Ace cards with conditionally differing values
     */
    object Ace : Rank(symbol = "A") {
        override fun value(currentSum: Int) = if (currentSum + 11 > TARGET_SUM) {
            1
        } else {
            11
        }
    }
}
