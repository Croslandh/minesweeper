Minesweeper

Project Description

This project is a Java-based Minesweeper game that will use JavaFX for its graphical user interface. The goal of the game is to reveal all the safe cells on a randomly generated board without clicking on any hidden mines.

Each cell can contain a mine or display a number indicating how many mines are adjacent to it. Players can reveal cells or flag cells they believe contain mines. The game will also include different difficulty settings that change the board dimensions and number of mines.


Planned Features:

Random Mine Generation: Mines will be randomly placed the board.

First-Click Safety: The first selected cell and its surrounding area will be protected from mines.

Adjacent Mine Counts: Revealed cells will display the number of neighboring mines.

Flagging System: Players can mark cells they suspect contain mines.

Game Modes: Players can switch between Reveal Mode and Flag Mode using the Shift key.

Difficulty Settings: Different board sizes and mine counts will be available.

Win/Loss Detection: The game will detect when the player wins or reveals a mine.

Best Times: The game will track the player's best completion times for each difficulty.

Object-Oriented Design

The project will use several classes to organize the game logic:

MinesweeperGame: Manages the overall game state, player interactions, and game modes.

GameBoard: Creates the board, places mines, calculates adjacent mine counts, and handles cell revealing.

Cell: Stores information about individual cells, including mine placement, reveal status, flag status, and adjacent mine counts.

Difficulty: Stores the board dimensions and mine count for each difficulty setting.

ScoreManager: Saves and retrieves the player's best completion times.

GameMode: An enum that tracks whether the player is in Reveal Mode or Flag Mode.



Java

JavaFX

Maven

JUnit

