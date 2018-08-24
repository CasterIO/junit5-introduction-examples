package de.mannodermaus.example.common

/**
 * Encapsulates the game logic behind the application.
 * Multiple sessions can be opened per game, and after completing them,
 * a winner is determined through use of the complete() method.
 */
class Blackjack(private val deck: Deck) {

    private var sessions = mutableListOf<Session>()
    private var completed = false

    /**
     * Open a new game session for the given player.
     */
    fun newSession(player: Player): Session {
        if (completed) {
            throw IllegalStateException("game already completed")
        }

        val session = Session(deck, player)
        sessions.add(session)
        return session
    }

    /**
     * Complete the game. Will return the winner by examining all recorded sessions,
     * or null if it's a draw
     */
    fun complete(): GameResult {
        if (completed) {
            throw IllegalStateException("game already completed")
        }
        completed = true

        val sortedSessions = sessions
                .filter { it.score <= TARGET_SUM }
                .sortedByDescending { it.score }

        // Check if multiple people have the same score
        return if (sortedSessions.isEmpty()) {
            // Nobody won
            GameResult.Draw
        } else {
            val firstSession = sortedSessions[0]
            val sessionsWithHighestScore = sortedSessions.filter { it.score == firstSession.score }
            if (sessionsWithHighestScore.size > 1) {
                // Multiple players have the same score
                GameResult.Draw
            } else {
                // One clear winner
                GameResult.OneWinner(firstSession.player)
            }
        }
    }
}

class Session(private val deck: Deck, val player: Player) {

    private val hand = Hand()

    private var _turn: Int = 0
    val turn get() = _turn

    val score: Int get() = hand.sum
    val currentCards get() = hand.cards

    fun makeTurn(): TurnResult {
        _turn++
        val newCard = deck.draw()
        hand += newCard

        val newSum = hand.sum
        return when {
            newSum > TARGET_SUM -> {
                // Went over the target
                TurnResult.Busted(newCard, newSum)
            }
            newSum == TARGET_SUM -> {
                // Hit the Blackjack target exactly
                TurnResult.TargetHit(newCard)
            }
            else -> {
                // Valid turn
                TurnResult.Success(newCard)
            }
        }
    }
}

sealed class TurnResult(val newCard: Card) {
    class Success(newCard: Card) : TurnResult(newCard)
    class TargetHit(newCard: Card) : TurnResult(newCard)
    class Busted(newCard: Card, val sum: Int) : TurnResult(newCard)
}

sealed class GameResult {
    class OneWinner(val player: Player) : GameResult()
    object Draw : GameResult()
}
