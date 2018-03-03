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
     * Constructs the square board.
     * @param size size of height and width.
     */
    public Board(int size) {
        this.width = size;
        this.height = size;
        this.board = new ReentrantLock[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new ReentrantLock();
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

    public static void main(String[] args) {
        Board board = new Board(10);
        Hero hero1 = new Hero("Player-1", board, 0, 0);
        Hero hero2 = new Hero("Player-2", board, 5, 5);

        Thread t1 = new Thread(hero1, "hero1");
        Thread t2 = new Thread(hero2, "hero2");

        t1.start();
        t2.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hero1.stopMovement();
        hero2.stopMovement();
    }
}
