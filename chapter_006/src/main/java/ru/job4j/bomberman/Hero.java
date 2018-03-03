package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Hero implements Runnable {
    /**
     * Name of the player.
     */
    private final String name;
    /**
     * Play board.
     */
    private final Board board;
    /**
     * X coordinate.
     */
    private int posX;
    /**
     * Y coordinate.
     */
    private int posY;
    /**
     * Current position.
     */
    private ReentrantLock currentCell;
    /**
     * Stop flag.
     */
    private volatile boolean stopped;

    /**
     * Constructs hero.
     * @param name name.
     * @param board board.
     * @param posX starting x coordinate.
     * @param posY starting y coordinate.
     */
    public Hero(String name, Board board, int posX, int posY) {
        this.name = name;
        this.board = board;
        this.posX = posX;
        this.posY = posY;
        this.currentCell = this.board.getCell(posX, posY);
    }

    /**
     * Thread start point.
     */
    public void run() {
        this.currentCell.lock();

        while (!stopped) {
            int[] direction = getDirection();
            int newX = this.posX + direction[0];
            int newY = this.posY + direction[1];

            if (newX >= 0 && newX < this.board.getWidth() && newY >= 0 && newY < this.board.getHeight()) {
                try {
                    moveHero(newX, newY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Chooses random direction for next turn.
     * @return array with x and y coordinates change.
     */
    private int[] getDirection() {
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
     * Метод перемещения
     * @param moveX new x position
     * @param moveY new y position
     * @throws InterruptedException
     */
    private void moveHero(int moveX, int moveY) throws InterruptedException {
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
     * Stops movement
     */
    public void stopMovement() {
        Thread.currentThread().interrupt();
        this.stopped = true;
    }
}
