Here is an improved **README.md** that *also includes your Git history summary* in a clean, readable **Project Evolution Timeline** section.

Everything is formatted professionally, easy to read, and suitable for GitHub.

---

# 🎮 Rock Paper Scissors (+ Well) — JavaFX Game

A modern, redesigned JavaFX version of **Rock–Paper–Scissors**, extended with the special **Well** mechanic.

Created by **Lukas Kreismair** and **Mhoreth**
(HTL Steyr)

---

## ✨ Features

### 🪨📄✂️🕳️ 4-Weapon Variant

This game is based on the classic version, but includes **WELL**, which changes the strategy:

| Weapon       | Beats          | Loses to    |
| ------------ | -------------- | ----------- |
| **Rock**     | Scissors       | Paper, Well |
| **Paper**    | Rock, Well     | Scissors    |
| **Scissors** | Paper          | Rock, Well  |
| **Well**     | Rock, Scissors | Paper       |

The bot chooses randomly using the `Computer` class.

---

### 🎶 Integrated Music Player

* Select between multiple songs
* No overlapping sounds
* Infinite loop (background music)
* Controlled via `ChoiceBox`

---

### ⭐ Highscore System

* Saved in `highscore.txt`
* Automatically updated when the player beats their record
* Persists between application runs

---

### 🎨 UI / Design Improvements

* Fully designed FXML layout
* Hover effects for buttons
* Styled choice box
* Custom weapon images
* Progress bar animation before showing the match result
* Ready for CSS theming (e.g., gradient background)

---

## 🚀 Gameplay Flow

1. Player selects weapon (Rock, Paper, Scissors, Well)
2. Progress bar appears with a random duration
3. Bot chooses random weapon
4. Result is shown (`YOU WON`, `THE BOT WON`, `ITS A DRAW`)
5. Score and highscore update automatically

---

## 📁 Project Structure (Code Overview)

```
src/
 └── main/
     ├── java/htl/steyr/rockpaperscissors_mhoreth_lkreisma/
     │     ├── GameController.java
     │     ├── Computer.java
     │     ├── Highscore.java
     │     ├── GameApplication.java
     │     └── ...
     ├── resources/
     │     ├── hello-view.fxml
     │     ├── pictures/
     │     ├── music/
     │     └── highscore.txt
```

---

# 📜 Project Evolution Timeline (Git History Summary)

This timeline is based on your Git log and shows how the project grew over time.

### 🟩 **Design Phase**

* Added design branch and updated FXML
* Added images for weapons
* Styled UI (hover effects, choicebox styling)
* Reworked picture paths
* Completed overall design

### 🟦 **Gameplay Mechanics**

* Added weapon selection
* Added bot logic (`Computer` class)
* Implemented winner calculation
* Added result display ("VS", winner label, etc.)
* Added image views for chosen weapons

### 🟨 **Progress Bar Feature**

* Added animated progress bar
* Synced progress bar with weapon reveal
* Styled progress bar
* Cleaned up the controller logic

### 🟪 **Highscore Feature**

* Added `Highscore` class
* Implemented reading/writing `highscore.txt`
* Added score and highscore labels
* Updated logic to store new highscore only when beaten

### 🟧 **Music Feature**

* Added JavaFX MediaPlayer
* Multiple songs
* Corrected overlapping audio issue
* Global musicChoiceBox handling
* Added comments for clarity

### 🟥 **Merges & Branches**

* Merged `music_feature`, `highscore_feature`, `designing`, and `well-feature`
* Rebasing & conflict resolutions
* Final polishing commits

This timeline shows the natural growth of the project—from basic layout → game logic → design → audio → highscore → final polishing.

---

## ▶️ How to Run

### Requirements

* **Java 17+**
* **JavaFX 17+**
* IntelliJ IDEA or Maven setup

### Steps

1. Clone repository
2. Open project in IDE
3. Ensure JavaFX libraries are configured
4. Run `GameApplication.java`

---

## Authors:

**Lkreisma**
**Mhoreth**

HTL Steyr — School Project

---

## 📄 License

Free to use for educational purposes.
Feel free to modify or extend.

---
