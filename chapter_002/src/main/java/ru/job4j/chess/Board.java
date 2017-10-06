package ru.job4j.chess;

public class Board {
    private Figure[][] board = new Figure[8][8];

    private int count = 0;

    private Figure[] figures = new Figure[16];

    public void figureTouch(Figure figure) {
        if (count < 16) {
            figures[count++] = figure;
        }
    }

    public Figure[] getFigures() {
        return figures;
    }

    public Figure[][] getBoard() {
        return board;
    }

    public void figureSetInBoard() {
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] != null) {
                //board[figures[i].getPosition().getHorizontal()][figures[i].getPosition().getVertical()] = figures[i];
            } else {
                break;
            }
        }
    }

    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean move = false;
        if (board[source.getVertical()][source.getHorizontal()] != null) {
            Figure figure = board[source.getHorizontal()][source.getVertical()];
            Cell[] cellsGo = figure.way(dist);
            for (Cell next : cellsGo) {
                if (board[next.getHorizontal()][next.getVertical()] == null) {
                    move = true;
                } else {
                    throw new OccupiedWayException("На данном пути стоит фигура");
                }
            }
        } else {
            throw new FigureNotFoundException("В данном поле нет фигуры");
        }
        return move;
    }
}
