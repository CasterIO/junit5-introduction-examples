package de.mannodermaus.example.android

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import de.mannodermaus.example.android.BlackjackActivity.Companion.EXTRA_PLAYER1_NAME
import de.mannodermaus.example.android.BlackjackActivity.Companion.EXTRA_PLAYER2_NAME
import de.mannodermaus.example.common.*
import kotlinx.android.synthetic.main.activity_blackjack.*

/**
 * GUI implementation of a simple Blackjack game.
 * Uses a retained fragment for configuration changes
 */
class BlackjackActivity : AppCompatActivity(), BlackjackCallbacks {

    companion object {
        const val EXTRA_PLAYER1_NAME = "player1-name"
        const val EXTRA_PLAYER2_NAME = "player2-name"

        private const val FRAGMENT_TAG = "data-fragment"
    }

    /* State */

    private lateinit var gameHandler: BlackjackFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blackjack)

        // Restore state, if necessary
        val player1 = intent.getStringExtra(EXTRA_PLAYER1_NAME)
        val player2 = intent.getStringExtra(EXTRA_PLAYER2_NAME)

        val fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as BlackjackFragment?
        if (fragment == null) {
            val newFragment = BlackjackFragment()
            newFragment.arguments = Bundle().apply {
                putString(EXTRA_PLAYER1_NAME, player1)
                putString(EXTRA_PLAYER2_NAME, player2)
            }
            supportFragmentManager.beginTransaction()
                    .add(newFragment, FRAGMENT_TAG)
                    .commit()
            this.gameHandler = newFragment
        } else {
            this.gameHandler = fragment
        }
    }

    fun onHitButtonClicked(unused: View) {
        gameHandler.drawAnotherCard()
    }

    fun onStandButtonClicked(unused: View) {
        gameHandler.completeCurrentSession()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?) =
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    AlertDialog.Builder(this)
                            .setTitle(R.string.dialogConfirmCancel)
                            .setMessage(R.string.dialogConfirmCancelMessage)
                            .setPositiveButton(R.string.buttonBackToMainMenu) { _, _ -> finish() }
                            .setNegativeButton(R.string.buttonCancel) { dialog, _ -> dialog.dismiss() }
                            .setCancelable(false)
                            .show()
                    false
                }
                else -> super.onKeyDown(keyCode, event)
            }

    /* BlackjackCallbacks */

    override fun onSessionStarted(player: Player) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialogGameStarted, player))
                .setMessage(getString(R.string.dialogGameStartedMessage))
                .setCancelable(false)
                .setPositiveButton(R.string.buttonStart) { _, _ -> gameHandler.drawAnotherCard() }
                .show()
    }

    override fun onSessionUpdated(player: Player, cards: List<Card>, score: Int, turnNumber: Int?) {
        supportActionBar?.title = if (turnNumber != null)
            getString(R.string.headerPlayerWithTurn, player, turnNumber)
        else
            player
        tvHand.text = cards.toString()
        tvSum.text = score.toString()
    }

    override fun onSessionCardDrawn(newCard: Card) {
        Toast.makeText(this, getString(R.string.toastCardDraw, newCard), Toast.LENGTH_SHORT).show()
    }

    override fun onSessionBusted(player: Player, score: Int) {
        AlertDialog.Builder(this)
                .setTitle(R.string.dialogGameLost)
                .setMessage(getString(R.string.dialogGameLostMessage, score))
                .setCancelable(false)
                .setPositiveButton(R.string.buttonOk) { _, _ ->
                    gameHandler.completeCurrentSession()
                }
                .show()
    }

    override fun onSessionHitTarget(player: Player) {
        AlertDialog.Builder(this)
                .setTitle(R.string.dialogGameExactlyBlackjack)
                .setMessage(getString(R.string.dialogGameExactlyBlackjackMessage, TARGET_SUM))
                .setCancelable(false)
                .setPositiveButton(R.string.buttonOk) { _, _ ->
                    gameHandler.completeCurrentSession()
                }
                .show()
    }

    override fun onSessionCompleted() {
    }

    override fun onGameCompleted(gameResult: GameResult) {
        val winner = when (gameResult) {
            is GameResult.OneWinner -> gameResult.player
            is GameResult.Draw -> getString(R.string.dialogGameResultDraw)
        }
        AlertDialog.Builder(this)
                .setTitle(R.string.dialogGameCompleted)
                .setMessage(getString(R.string.dialogGameResult, winner))
                .setCancelable(false)
                .setPositiveButton(R.string.buttonBackToMainMenu) { _, _ ->
                    finish()
                }
                .show()
    }
}

interface BlackjackCallbacks {
    fun onSessionStarted(player: Player)
    fun onSessionUpdated(player: Player, cards: List<Card>, score: Int, turnNumber: Int?)
    fun onSessionCardDrawn(newCard: Card)
    fun onSessionBusted(player: Player, score: Int)
    fun onSessionHitTarget(player: Player)
    fun onSessionCompleted()
    fun onGameCompleted(gameResult: GameResult)
}

interface BlackjackFunctions {
    fun completeCurrentSession()
    fun drawAnotherCard()
}

class BlackjackFragment : Fragment(), BlackjackFunctions {

    private lateinit var callbacks: BlackjackCallbacks
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var game: Blackjack

    private var currentPhase: Int = 1

    private var _currentSession: Session? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BlackjackCallbacks) {
            this.callbacks = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true

        this.player1 = arguments?.getString(EXTRA_PLAYER1_NAME) ?: throw IllegalArgumentException()
        this.player2 = arguments?.getString(EXTRA_PLAYER2_NAME) ?: throw IllegalArgumentException()

        // Start with the initial state
        startNewGame()
        nextPhase()
    }

    override fun onResume() {
        super.onResume()

        _currentSession?.let {
            callbacks.onSessionUpdated(it.player, it.currentCards, it.score, it.turn.orNullIfBelow(0))
        }
    }

    private fun startNewGame() {
        val deck = Deck(ALL_CARDS)
        deck.shuffle()
        this.game = Blackjack(deck)
        this.currentPhase = 1
    }

    private fun nextPhase() {
        when (currentPhase) {
            1 -> startNewSessionFor(player1)
            2 -> startNewSessionFor(player2)
            3 -> completeGame()
            else -> throw IllegalStateException()
        }
        currentPhase++
    }

    private fun startNewSessionFor(player: Player) {
        val newSession = game.newSession(player)
        this._currentSession = newSession
        callbacks.onSessionStarted(player)
    }

    private fun completeGame() {
        val result = game.complete()
        callbacks.onGameCompleted(result)
        startNewGame()
    }

    override fun completeCurrentSession() {
        // Iterate to the next state
        this._currentSession = null
        nextPhase()
    }

    override fun drawAnotherCard() {
        val session = _currentSession ?: throw IllegalStateException()
        val turnResult = session.makeTurn()
        callbacks.onSessionUpdated(session.player, session.currentCards, session.score, session.turn.orNullIfBelow(0))
        callbacks.onSessionCardDrawn(turnResult.newCard)

        when (turnResult) {
            is TurnResult.TargetHit -> callbacks.onSessionHitTarget(session.player)
            is TurnResult.Busted -> callbacks.onSessionBusted(session.player, turnResult.sum)
        }
    }
}
