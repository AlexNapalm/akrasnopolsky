package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Board {
    /**
     * Array of "cells" - ReentrantLocks.
     */
    final ReentrantLock[][] board;
    /**
     * Width of the board.
     */
    final int width;
    /**
     * Height of the board.
     */
    final int height;

    /**
     * Constructs board.
     * @param width width.
     * @param height height.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        board = new ReentrantLock[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Gets the cell of the board.
     * @param x x coordinate.
     * @param y y coordinate.
     * @return the cell.
     */
    public ReentrantLock getCell(int x, int y) {
        return this.board[y][x];
    }

    /**
     * Gets the width.
     * @return width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height.
     * @return height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Locks the cell.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public void blockCell(int x, int y) {
        this.getCell(x, y).lock();
    }
}
