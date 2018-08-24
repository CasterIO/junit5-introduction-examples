package de.mannodermaus.example.common.test.nested

import de.mannodermaus.example.common.ALL_CARDS
import de.mannodermaus.example.common.Deck
import de.mannodermaus.example.common.Session
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue

@DisplayName("Nested: Given a Deck")
class NestedTests {

    private lateinit var deck: Deck

    @BeforeEach
    fun onParentBeforeEach() {
        deck = Deck(ALL_CARDS)
        println("@BeforeEach called on the outer class")
    }

    @AfterEach
    fun onParentAfterEach() {
        println("@AfterEach called on the outer class")
    }

    @Nested
    @DisplayName("When a Session is created from it")
    inner class SessionTurnTests {

        private lateinit var session: Session

        @BeforeEach
        fun onChildBeforeEach() {
            session = Session(deck, "Player 1")
            println("    @BeforeEach called on the inner class")
        }

        @AfterEach
        fun onChildAfterEach() {
            println("    @AfterEach called on the inner class")
        }

        @Test
        @DisplayName("It will keep track of the current turn")
        fun checkThatTurnNumberWillIncrease() {
            println("        Executing first test method in the inner class")

            val initialTurn = session.turn

            session.makeTurn()

            assertTrue(session.turn == initialTurn + 1)
        }

        @Test
        @DisplayName("It will keep track of the current score")
        fun checkThatScoreWillIncrease() {
            println("        Executing second test method in the inner class")

            val initialScore = session.score

            session.makeTurn()

            assertTrue(session.score > initialScore)
        }
    }
}
