# 🔠 Kotlin Wordle Game

This is a simple Wordle-style game built using **Kotlin** for Android. The app fetches a secret word from an API, and the player has to guess it within a limited number of attempts. It's a clean and fun mini-project to demonstrate API integration, state management, and UI logic in Android using Kotlin.

---

## 🎮 Game Rules (Wordle Style)

- The goal is to **guess the hidden word** (usually 5 letters) in a limited number of tries.
- Each guess must be a valid word.
- After each guess, you receive feedback:
  - 🟩 **Green** – The letter is in the correct spot.
  - 🟨 **Yellow** – The letter is in the word but in the wrong position.
  - ⬜ **Gray** – The letter is not in the word at all.
- You win if you guess the word before running out of attempts.

---

## 🔧 Features

- 🎯 Real Wordle logic implemented in Kotlin
- 🌐 Connected to an API that returns a random word
- 🧠 Input validation and visual feedback after each guess
- 📱 Built with a simple and responsive UI using Jetpack Compose

---

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Backend**: Word API to fetch the word of the day

---

## 🚀 How to Run

1. Clone the repo:

```bash
git clone https://github.com/yourusername/kotlin-wordle-game.git
