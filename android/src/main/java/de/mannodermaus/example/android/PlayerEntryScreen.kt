package de.mannodermaus.example.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_player_entry.*

class PlayerEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_entry)
        supportActionBar?.title = getString(R.string.app_name)
    }

    fun onPlayButtonClicked(ignored: View) {
        startActivity(
                Intent(this, BlackjackActivity::class.java).apply {
                    val player1Name = if (etPlayer1.text.isNotEmpty())
                        etPlayer1.text.toString()
                    else
                        getString(R.string.defaultNamePlayer1)
                    val player2Name = if (etPlayer2.text.isNotEmpty())
                        etPlayer2.text.toString()
                    else
                        getString(R.string.defaultNamePlayer2)
                    putExtra(BlackjackActivity.EXTRA_PLAYER1_NAME, player1Name)
                    putExtra(BlackjackActivity.EXTRA_PLAYER2_NAME, player2Name)
                })
    }
}
