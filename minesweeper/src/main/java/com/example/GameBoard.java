package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private final Cell[][] cells;
    private final int rows;
    private final int columns;
    private final int mineCount;
    private boolean minesPlaced;
    private final Random random;

    public GameBoard(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null.");
        }

        rows = difficulty.getRows();
        columns = difficulty.getColumns();
        mineCount = difficulty.getMineCount();
        cells = new Cell[rows][columns];
        minesPlaced = false;
        random = new Random();
        createBoard();
    }

    public void createBoard() {
        minesPlaced = false;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    public void placeMines(int safeRow, int safeCol) {
        if (minesPlaced) {
            return;
        }
        if (!isInBounds(safeRow, safeCol)) {
            return;
        }

        List<int[]> candidates = new ArrayList<int[]>();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column].setMine(false);
                if (!isInProtectedArea(row, column, safeRow, safeCol)) {
                    candidates.add(new int[]{row, column});
                }
            }
        }

        if (candidates.size() < mineCount) {
            throw new IllegalArgumentException(
                    "Mine count cannot fit outside the protected first-click area.");
        }

        for (int index = candidates.size() - 1; index > 0; index--) {
            int swapIndex = random.nextInt(index + 1);
            int[] temporary = candidates.get(index);
            candidates.set(index, candidates.get(swapIndex));
            candidates.set(swapIndex, temporary);
        }

        for (int index = 0; index < mineCount; index++) {
            int[] position = candidates.get(index);
            cells[position[0]][position[1]].setMine(true);
        }

        calculateAllAdjacentMines();
        minesPlaced = true;
    }

    public int countAdjacentMines(int row, int col) {
        if (!isInBounds(row, col)) {
            return 0;
        }

        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                int neighborRow = row + rowOffset;
                int neighborColumn = col + columnOffset;
                if (isInBounds(neighborRow, neighborColumn)
                        && cells[neighborRow][neighborColumn].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void calculateAllAdjacentMines() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (cells[row][column].isMine()) {
                    cells[row][column].setAdjacentMineCount(0);
                } else {
                    cells[row][column].setAdjacentMineCount(
                            countAdjacentMines(row, column));
                }
            }
        }
    }

    public void revealCell(int row, int col) {
        if (!isInBounds(row, col)) {
            return;
        }

        Cell cell = cells[row][col];
        if (cell.isFlagged() || cell.isRevealed()) {
            return;
        }

        cell.reveal();
        if (cell.isMine() || cell.getAdjacentMineCount() != 0) {
            return;
        }

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }

                int neighborRow = row + rowOffset;
                int neighborColumn = col + columnOffset;
                if (isInBounds(neighborRow, neighborColumn)) {
                    Cell neighbor = cells[neighborRow][neighborColumn];
                    if (!neighbor.isRevealed() && !neighbor.isFlagged()
                            && !neighbor.isMine()) {
                        revealCell(neighborRow, neighborColumn);
                    }
                }
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (!isInBounds(row, col)) {
            return null;
        }
        return cells[row][col];
    }

    public boolean hasMinesPlaced() {
        return minesPlaced;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMineCount() {
        return mineCount;
    }

    private boolean isInProtectedArea(int row, int col, int safeRow, int safeCol) {
        return Math.abs(row - safeRow) <= 1
                && Math.abs(col - safeCol) <= 1;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }
}
