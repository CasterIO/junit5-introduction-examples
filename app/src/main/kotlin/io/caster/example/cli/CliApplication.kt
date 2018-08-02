package io.caster.example.cli

import io.caster.example.*

/**
 * Runs the game logic for the given player.
 * Returns their result, or null if they lose
 */
private fun runGameLoop(blackjack: Blackjack, player: Player, scoreToBeat: Int? = null): Int? {
    // Open a new game session & handle the player's input
    val session = blackjack.newSession(player)

    while (true) {
        val currentSum = session.score

        println("--------")
        println("Let's go, $player!")
        println("Turn #${session.turn}")
        println("Your hand: ${session.currentCards} (sum: $currentSum)")
        scoreToBeat?.let { println("(Score To Beat: $it)") }
        println("--------")

        inputLoop@ while (true) {
            when (readLine("You can [h]it to draw another card, or [s]tand to stop drawing:")) {
                "h" -> {
                    val turnResult = session.makeTurn()
                    println("HIT: You drew a ${turnResult.newCard}")
                    println()

                    return when (turnResult) {
                        is TurnResult.Success -> {
                            break@inputLoop
                        }
                        is TurnResult.TargetHit -> {
                            println("Blackjack! You got exactly $TARGET_SUM. Good job!")
                            TARGET_SUM
                        }
                        is TurnResult.Busted -> {
                            println("Busted! You hit ${turnResult.sum}. Better luck next time!")
                            kotlin.io.readLine()
                            null
                        }
                    }
                }
                "s" -> {
                    val finalScore = session.score
                    println("STAND: Your final score is $finalScore.")
                    println()
                    return finalScore
                }
                else -> println("???")
            }
        }
    }
}

/**
 * Main method
 */
fun main(args: Array<String>) {
    // 1) Prepare deck
    // 2) Name entry
    // 3) Shuffle deck
    // 4) Start playing
    val deck = Deck(ALL_CARDS)

    println("""

        -------------------
        | ♥ ♣ ♦ ♠ ♥ ♣ ♦ ♠ |
        |  Blackjack CLI  |
        | ♥ ♣ ♦ ♠ ♥ ♣ ♦ ♠ |
        -------------------

    """.trimIndent())

    val player1: Player = readLine("Player 1, please enter your name:")
            ?: "Player 1"
    val player2: Player = readLine("Player 2, please enter your name:")
            ?: "Player 2"

    deck.shuffle()
    val game = Blackjack(deck)

    val player1Result = runGameLoop(game, player1)
    val player2Result = runGameLoop(game, player2, scoreToBeat = player1Result)

    println("Game! Let's compare scores:")
    println("$player1's result: ${player1Result ?: "BUSTED"}")
    println("$player2's result: ${player2Result ?: "BUSTED"}")

    val gameResult = game.complete()
    when (gameResult) {
        is GameResult.OneWinner -> {
            println()
            println("And the winner is...")
            println("-".repeat(gameResult.player.length + 6))
            println("|  ${gameResult.player}  |")
            println("-".repeat(gameResult.player.length + 6))
            println()
        }
        is GameResult.Draw -> {
            println("""

            ------------
            |  Draw!!  |
            ------------

            """.trimIndent())
        }
    }

    println("Thanks for playing! :)")
}
