package com.example;

public class Main {
    public static void main(String[] args) {
        ScoreManager scoreManager = new ScoreManager();
        MinesweeperGame game = new MinesweeperGame(Difficulty.BEGINNER);
        game.startNewGame();

        GameBoard board = game.getBoard();
        Difficulty difficulty = game.getDifficulty();

        System.out.println("Minesweeper Console Demonstration");
        System.out.println("Board size: " + board.getRows() + " rows x "
                + board.getColumns() + " columns");
        System.out.println("Mine count: " + board.getMineCount());
        printGameState(game);

        game.toggleMode();
        System.out.println("Switched mode to: " + game.getCurrentMode());
        printGameState(game);

        int flagRow = 0;
        int flagColumn = 0;
        game.handleCellClick(flagRow, flagColumn);
        Cell flaggedCell = board.getCell(flagRow, flagColumn);
        System.out.println("Flagged cell (" + flagRow + ", " + flagColumn + ")");
        System.out.println("Flagged: " + flaggedCell.isFlagged());
        printGameState(game);

        game.toggleMode();
        System.out.println("Switched mode to: " + game.getCurrentMode());
        printGameState(game);

        int firstRow = 4;
        int firstColumn = 4;
        game.handleCellClick(firstRow, firstColumn);
        Cell firstClickedCell = board.getCell(firstRow, firstColumn);

        System.out.println("Revealed first-clicked cell (" + firstRow + ", "
                + firstColumn + ")");
        System.out.println("First cell is mine: " + firstClickedCell.isMine());
        System.out.println("First cell is revealed: " + firstClickedCell.isRevealed());
        System.out.println("Adjacent mines: " + firstClickedCell.getAdjacentMineCount());
        System.out.println("The clicked 3x3 area is protected from mines.");
        printGameState(game);

        int sampleTime = 125;
        scoreManager.saveBestTime(difficulty, sampleTime);
        System.out.println("Saved sample best time: " + sampleTime + " seconds");
        System.out.println("Retrieved best time: "
                + scoreManager.getBestTime(difficulty) + " seconds");
    }

    private static void printGameState(MinesweeperGame game) {
        System.out.println("Mode: " + game.getCurrentMode()
                + " | Game over: " + game.isGameOver());
    }
}
