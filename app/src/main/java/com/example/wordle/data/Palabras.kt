package com.example.wordle.data

import com.example.wordle.ui.viewmodel.CharState

data class CharResult(val char: Char, val state: CharState)

data class Root(
    val word: String,
)

