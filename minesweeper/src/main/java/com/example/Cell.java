package com.example;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMineCount;

    public Cell() {
        this.isMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.adjacentMineCount = 0;
    }

    public void toggleFlag() {
        if (!isRevealed) {
            isFlagged = !isFlagged;
        }
    }

    public void reveal() {
        if (!isFlagged) {
            isRevealed = true;
        }
    }

    public void setMine(boolean value) {
        isMine = value;
    }

    public void setAdjacentMineCount(int count) {
        adjacentMineCount = count;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public int getAdjacentMineCount() {
        return adjacentMineCount;
    }
}
