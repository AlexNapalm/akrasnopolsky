package ru.job4j.chess;

public abstract class Figure {
    final Cell position;
    private String color;

    public Figure(int horizontal, int vertical) {
        position = new Cell(horizontal, vertical);
    }

    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;


}
