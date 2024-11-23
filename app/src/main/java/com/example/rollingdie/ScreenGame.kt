package com.example.rollingdie

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.rollingdie.databinding.GameScreenBinding
import kotlin.compareTo
import kotlin.toString


class ScreenGame : Fragment() {

    private var _binding: GameScreenBinding? = null
    private val binding get() = _binding!!

    private var currentPlayer = 1
    private var currentTurnScore = 0
    private var player1TotalScore = 0
    private var player2TotalScore = 0
    private var gameWon = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = GameScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            currentPlayer = savedInstanceState.getInt("currentPlayer")
            currentTurnScore = savedInstanceState.getInt("currentTurnScore")
            player1TotalScore = savedInstanceState.getInt("player1TotalScore")
            player2TotalScore = savedInstanceState.getInt("player2TotalScore")
            gameWon = savedInstanceState.getBoolean("gameStatus")
            updateCurrentScore()
            updateTurnInfo()
            binding.player1Total.text = player1TotalScore.toString()
            binding.player2Total.text = player2TotalScore.toString()
            if (gameWon) {
                binding.rollButton.isEnabled = false
                binding.passButton.isEnabled = false
            }
        }

        binding.backButton.setOnClickListener {
            val action = ScreenGameDirections.backToNewGame()
            Navigation.findNavController(it).navigate(action)
        }

        binding.rollButton.setOnClickListener {
            rollDie()
        }

        binding.passButton.setOnClickListener {
            passTurn()
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rollDie() {
        val roll = (1..6).random()
        binding.resultText.text = roll.toString()

        if (roll == 1 || roll == 6) {
            currentTurnScore = 0
            Toast.makeText(context, if (currentPlayer == 1) "Player 2's Turn" else "Player 1's Turn", Toast.LENGTH_SHORT).show()
            endTurn()
        } else {
            currentTurnScore += roll
            updateCurrentScore()
            if (currentPlayer == 1 && player1TotalScore + currentTurnScore >= 10) {
                declareWinner(1)
            } else if (currentPlayer == 2 && player2TotalScore + currentTurnScore >= 10) {
                declareWinner(2)
            }
        }
    }

    private fun passTurn() {
        if (currentPlayer == 1) {
            player1TotalScore += currentTurnScore
            binding.player1Total.text = player1TotalScore.toString()
            if (player1TotalScore >= 10) {
                declareWinner(1)
            }
        } else {
            player2TotalScore += currentTurnScore
            binding.player2Total.text = player2TotalScore.toString()
            if (player2TotalScore >= 10) {
                declareWinner(2)
            }
        }
        Toast.makeText(context, if (currentPlayer == 1) "Player 2's Turn" else "Player 1's Turn", Toast.LENGTH_SHORT).show()
        currentTurnScore = 0
        endTurn()
    }

    private fun endTurn() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
        updateCurrentScore()
        updateTurnInfo()
    }

    private fun updateCurrentScore() {
        binding.currentScore.text = currentTurnScore.toString()
    }

    private fun updateTurnInfo() {
        binding.turnInfo.text = if (currentPlayer == 1) getString(R.string.player1Turn) else getString(R.string.player2Turn)
    }

    private fun declareWinner(player: Int) {
        binding.resultText.text = getString(R.string.winner, player)
        binding.rollButton.isEnabled = false
        binding.passButton.isEnabled = false
        gameWon = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPlayer", currentPlayer)
        outState.putInt("currentTurnScore", currentTurnScore)
        outState.putInt("player1TotalScore", player1TotalScore)
        outState.putInt("player2TotalScore", player2TotalScore)
        outState.putBoolean("gameStatus", gameWon)
    }
}