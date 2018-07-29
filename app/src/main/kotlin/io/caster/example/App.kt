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
 * Runs the game logic for the given player.
 * Returns their result, or null if they lose
 */
private fun runGameLoop(deck: Deck, player: Player, scoreToBeat: Int? = null): Int? {
    var playerTurn = 1

    while (true) {
        val currentSum = player.hand.sum()

        when {
            currentSum > TARGET_SUM -> {
                println("Busted! You have $currentSum. Better luck next time!")
                kotlin.io.readLine()
                return null
            }
            currentSum == TARGET_SUM -> {
                println(player.hand.cards)
                println("Blackjack! You win.")
                readLine()
                return TARGET_SUM
            }
            else -> {
                println("--------")
                println("Let's go, ${player.name}!")
                println("Turn #$playerTurn")
                println("Your hand: ${player.hand.cards} (sum: $currentSum)")
                scoreToBeat?.let { println("(Score To Beat: $it)") }
                println("--------")
                inputLoop@ while (true) {
                    when (readLine("You can [h]it to draw another card, or [s]tand to stop drawing:")) {
                        "h" -> {
                            val newCard = deck.draw()
                            println("HIT: You drew a $newCard")
                            println()
                            player.hand += newCard
                            playerTurn++
                            break@inputLoop
                        }
                        "s" -> {
                            println("STAND: Your final score is $currentSum.")
                            println()
                            return currentSum
                        }
                        else -> println("???")
                    }
                }
            }
        }
    }
}

/**
 * Given two players and their results in this round, determine who won (if anybody)
 */
fun determineWinner(player1: Player, player1Result: Int?, player2: Player, player2Result: Int?): Player? {
    return if (player1Result == null && player2Result == null) {
        // Nobody scored anything - no winner
        null
    } else {
        // At least 1 player scored something; assume 0 for non-scoring players and compare
        val player1Score = player1Result ?: 0
        val player2Score = player2Result ?: 0

        when {
            player1Score > player2Score -> player1
            player1Score < player2Score -> player2
            else -> player2
        }
    }
}

/**
 * Main method
 */
fun main(args: Array<String>) {
    // Create two players, sharing from a single deck of cards
    val deck = Deck(ALL_CARDS)

    // Start the game loop:
    // 1) Name entry
    // 2) Shuffle deck
    // 3) Start playing
    println("""

        -------------------
        | ♥ ♣ ♦ ♠ ♥ ♣ ♦ ♠ |
        |  Blackjack CLI  |
        | ♥ ♣ ♦ ♠ ♥ ♣ ♦ ♠ |
        -------------------

    """.trimIndent())

    val player1Name = readLine("Player 1, please enter your name:")
    val player2Name = readLine("Player 2, please enter your name:")

    deck.shuffle()

    val player1 = Player(name = player1Name ?: "Player 1", hand = Hand())
    val player2 = Player(name = player2Name ?: "Player 2", hand = Hand())

    val player1Result = runGameLoop(deck, player1)
    val player2Result = runGameLoop(deck, player2, scoreToBeat = player1Result)

    println("Game! Let's compare scores:")
    println("${player1.name}: ${player1.hand.cards} (result: ${player1Result ?: "BUSTED"})")
    println("${player2.name}: ${player2.hand.cards} (result: ${player2Result ?: "BUSTED"})")

    val winner = determineWinner(player1, player1Result, player2, player2Result)
    if (winner == null) {
        println("""

            ------------
            |  Draw!!  |
            ------------

        """.trimIndent())
    } else {
        println()
        println("And the winner is...")
        println("-".repeat(winner.name.length + 6))
        println("|  ${winner.name}  |")
        println("-".repeat(winner.name.length + 6))
        println()
    }

    println("Thanks for playing! :)")
}
