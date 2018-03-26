package ru.job4j.bomberman;

public class Monster extends Character {

    Monster(Board board, String name, int x, int y) {
        super(name, board, x, y);
    }

    public void run() {
        this.currentCell.lock();

        while (!stopped) {
            int[] direction = getDirection();
            int moveX = this.posX + direction[0];
            int moveY = this.posY + direction[1];

            if (moveX >= 0 && moveX < board.getWidth() && moveY >= 0 && moveY < board.getHeight()) {
                try {
                    moveCharacter(moveX, moveY);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
