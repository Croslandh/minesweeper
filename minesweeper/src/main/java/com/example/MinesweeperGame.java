package com.example;

public class MinesweeperGame {
    private GameBoard board;
    private Difficulty difficulty;
    private ScoreManager scoreManager;
    private GameMode currentMode;
    private boolean gameOver;

    public MinesweeperGame() {
        this(Difficulty.BEGINNER);
    }

    public MinesweeperGame(Difficulty difficulty) {
        this.difficulty = difficulty == null ? Difficulty.BEGINNER : difficulty;
        this.scoreManager = new ScoreManager();
        this.currentMode = GameMode.REVEAL;
        this.gameOver = false;
        this.board = new GameBoard(this.difficulty);
    }

    public void startNewGame() {
        board = new GameBoard(difficulty);
        currentMode = GameMode.REVEAL;
        gameOver = false;
    }

    public void toggleMode() {
        if (currentMode == GameMode.REVEAL) {
            currentMode = GameMode.FLAG;
        } else {
            currentMode = GameMode.REVEAL;
        }
    }

    public void handleCellClick(int row, int col) {
        if (gameOver || board == null) {
            return;
        }

        Cell cell = board.getCell(row, col);
        if (cell == null) {
            return;
        }

        if (currentMode == GameMode.FLAG) {
            cell.toggleFlag();
            return;
        }

        if (!board.hasMinesPlaced()) {
            board.placeMines(row, col);
        }

        board.revealCell(row, col);

        if (cell.isMine() && cell.isRevealed()) {
            endGame();
            return;
        }

        checkWin();
    }

    public boolean checkWin() {
        if (board == null) {
            return false;
        }

        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                Cell cell = board.getCell(row, column);
                if (!cell.isMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }

        if (!gameOver) {
            endGame();
        }
        return true;
    }

    public void endGame() {
        gameOver = true;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public GameMode getCurrentMode() {
        return currentMode;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty != null) {
            this.difficulty = difficulty;
            startNewGame();
        }
    }
}
