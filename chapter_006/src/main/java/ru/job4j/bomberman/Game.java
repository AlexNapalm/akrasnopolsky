package ru.job4j.bomberman;

import java.util.ArrayList;

public class Game {

    //игровое поле
    Board board;

    //список персонажей
    ArrayList<Character> characters;

    Game(int width, int height) {
        this.board = new Board(width, height);
        this.characters = new ArrayList<>();
    }

    /**
     * Creates main hero.
     * @param name name
     * @param x starting x coordinate.
     * @param y starting y coordinate.
     */
    public void createHero(String name, int x, int y) {
        this.characters.add(new Hero(name, board, x, y));
    }

    /**
     * Creates monster.
     * @param name name.
     * @param x starting x coordinate.
     * @param y starting y coordinate.
     */
    public void createMonster(String name, int x, int y) {
        characters.add(new Monster(board, name, x, y));
    }

    /**
     * Creates blocked area.
     * @param x blocked x coordinate.
     * @param y blocked y coordinate.
     */
    public void createBlockedArea(int x, int y) {
        this.board.blockCell(x, y);
        System.out.println(String.format("Blocked area coordinates x %s y %s", x, y));
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        for (Character c : characters) {
            new Thread(c, c.getName()).start();
        }
    }

    /**
     * Stops the game.
     */
    public void stopGame() {
        for (Character c : characters) {
            c.stopMovement();
        }
    }

    public static void main(String[] args) {
        Game game = new Game(10, 10);
        game.createBlockedArea(4, 6);
        game.createBlockedArea(8, 2);
        game.createHero("Hero", 5, 5);
        game.createMonster("Monster1", 3, 3);
        game.createMonster("Monster2", 7, 7);
        game.startGame();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.stopGame();
    }
}
