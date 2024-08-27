package com.example.wordle.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.data.CharResult
import com.example.wordle.ui.viewmodel.CharState
import com.example.wordle.ui.viewmodel.WordleGame

@Composable
fun WordleGameScreen() {
    var inputWord by remember { mutableStateOf("") }
    val game = remember { WordleGame(wordToGuess = "LACAL") }
    var errorMessage by remember { mutableStateOf("") }

    WordleGameView(
        inputWord = inputWord.uppercase() ,
        onWordChanged = { inputWord = it },
        onSubmitWord = {
            if (inputWord.length == 5) {
                game.submitWord(inputWord)
                inputWord = ""
                errorMessage = ""
            } else {
                errorMessage = "La palabra debe tener 5 caracteres."
            }
        },
        attempts = game.attempts,
        isGameOver = game.isGameOver,
        wordToGuess = game.wordToGuess,
        errorMessage = errorMessage,
        restartGame =  {
            game.resetGame()
            inputWord = ""
            errorMessage = ""
        }
    )
}
@Composable
fun WordleGameView(
    inputWord: String,
    onWordChanged: (String) -> Unit,
    onSubmitWord: () -> Unit,
    attempts: List<List<CharResult>>,
    isGameOver: Boolean,
    wordToGuess: String,
    errorMessage: String,
    restartGame: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Mostrar la tabla de palabras ingresadas con feedback en el centro
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            WordleAttemptsList(attempts)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Wordle en Android")

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }

        TextField(
            value = inputWord,
            onValueChange = onWordChanged,
            label = { Text("Introduce una palabra") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )

        Button(
            onClick = onSubmitWord,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Enviar intento")
        }
        Button( onClick = {restartGame}){
            Text("Reiniciar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isGameOver) {
            val message = if (attempts.lastOrNull()?.all { it.state == CharState.CORRECT } == true) {
                "¡Felicidades! Adivinaste la palabra."
            } else {
                "Juego Terminado. La palabra era: $wordToGuess"
            }
            Text(text = message)
        }
    }
}

@Composable
fun WordleAttemptsList(attempts: List<List<CharResult>>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(attempts) { attempt ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                attempt.forEach { charResult ->
                    val color = when (charResult.state) {
                        CharState.CORRECT -> Color.Green
                        CharState.PRESENT -> Color.Yellow
                        CharState.ABSENT -> Color.Gray
                    }
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .border(2.dp, Color.Black)
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = charResult.char.toString(), fontSize = 24.sp)
                    }
                }
            }
        }
    }
}


