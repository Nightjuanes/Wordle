package com.example.wordle.ui.viewmodel


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.wordle.ui.views.WordleGameView
import androidx.compose.runtime.setValue
import com.example.wordle.data.CharResult
import com.example.wordle.ui.views.WordleAttemptsList


enum class CharState {
    CORRECT, PRESENT, ABSENT
}


class WordleGame(val wordToGuess: String) {
    private val _attempts = mutableStateListOf<List<CharResult>>()
    val attempts: List<List<CharResult>> get() = _attempts
    var isGameOver by mutableStateOf(false)
        private set

    fun submitWord(inputWord: String) {
        if (inputWord.length != wordToGuess.length || isGameOver) return

        val feedback = generateFeedback(inputWord)
        _attempts.add(feedback)

        if (inputWord == wordToGuess || _attempts.size >= 6) {
            isGameOver = true
        }
    }
 private fun generateFeedback(inputWord: String): List<CharResult> {
        return inputWord.mapIndexed { index, char ->
            when {
                wordToGuess[index] == char -> CharResult(char, CharState.CORRECT)
                char in wordToGuess -> CharResult(char, CharState.PRESENT)
                else -> CharResult(char, CharState.ABSENT)
            }
        }
    }

    fun resetGame() {
        _attempts.clear()
        isGameOver = true
    }
}