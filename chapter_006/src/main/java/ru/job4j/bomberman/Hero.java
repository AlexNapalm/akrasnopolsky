package ru.job4j.bomberman;

public class Hero extends Character {

    /**
     * Constructs hero.
     * @param name name.
     * @param board board.
     * @param posX starting x coordinate.
     * @param posY starting y coordinate.
     */
    public Hero(String name, Board board, int posX, int posY) {
        super(name, board, posX, posY);
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
                    moveCharacter(newX, newY);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
