package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Character implements Runnable {

    /**
     * Board.
     */
    protected Board board;

    /**
     * Name.
     */
    protected String name;

    /**
     * X coordinate.
     */
    protected int posX;

    /**
     * Y coordinate.
     */
    protected int posY;

    /**
     * Current cell.
     */
    protected ReentrantLock currentCell;

    /**
     * Stop flag.
     */
    protected volatile boolean stopped = false;

    Character(String name, Board board, int x, int y) {
        this.board = board;
        this.currentCell = this.board.getCell(x, y);
        this.name = name;
        this.posX = x;
        this.posY = y;
    }

    /**
     * Chooses random direction for next turn.
     * @return array with x and y coordinates change.
     */
    protected int[] getDirection() {
        Random random = new Random();
        int[] move = new int[2];

        switch (random.nextInt(4)) {
            //right
            case 0:
                move[0] = 1;
                move[1] = 0;
                break;
            //left
            case 1:
                move[0] = -1;
                move[1] = 0;
                break;
            //up
            case 2:
                move[0] = 0;
                move[1] = 1;
                break;
            // down
            case 3:
                move[0] = 0;
                move[1] = -1;
                break;
            default: break;
        }
        return move;
    }

    /**
     * Moves hero.
     * @param moveX new x position
     * @param moveY new y position
     * @throws InterruptedException
     */
    protected void moveCharacter(int moveX, int moveY) throws InterruptedException {
        ReentrantLock cellToMove = this.board.getCell(moveX, moveY);
        boolean movePossible = cellToMove.tryLock(500, TimeUnit.MILLISECONDS);

        if (movePossible) {
            this.posX = moveX;
            this.posY = moveY;
            this.currentCell.unlock();
            this.currentCell = cellToMove;

            System.out.println(String.format("%s coordinates: x %s y %s", this.name, this.posX, this.posY));
            Thread.sleep(1000);
        }
    }

    /**
     * Метод получения имени
     * @return имя персонажа
     */
    protected String getName() {
        return this.name;
    }

    /**
     * Stops movement
     */
    protected void stopMovement() {
        Thread.currentThread().interrupt();
        this.stopped = true;
    }
}
