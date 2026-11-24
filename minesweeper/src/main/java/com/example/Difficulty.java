package com.example;

import java.util.Objects;

public class Difficulty {
    public static final Difficulty BEGINNER = new Difficulty(9, 9, 10);
    public static final Difficulty INTERMEDIATE = new Difficulty(16, 16, 40);
    public static final Difficulty EXPERT = new Difficulty(16, 30, 99);

    private final int rows;
    private final int columns;
    private final int mineCount;

    public Difficulty(int rows, int columns, int mineCount) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Board dimensions must be positive.");
        }
        if (mineCount < 0 || mineCount >= rows * columns) {
            throw new IllegalArgumentException("Mine count must fit on the board.");
        }

        this.rows = rows;
        this.columns = columns;
        this.mineCount = mineCount;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Difficulty)) {
            return false;
        }

        Difficulty other = (Difficulty) object;
        return rows == other.rows
                && columns == other.columns
                && mineCount == other.mineCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, columns, mineCount);
    }

    @Override
    public String toString() {
        return rows + "x" + columns + " / " + mineCount + " mines";
    }
}
