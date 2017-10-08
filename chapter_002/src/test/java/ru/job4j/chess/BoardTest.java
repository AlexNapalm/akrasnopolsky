package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    /**
     * Тест метода findCell
     */
    @Test
    public void whenFindCellThenGetCell() {
        Board board = new Board();
        Cell result = board.findCell(5, 5);
        Cell[][] expected = board.getCells();
        assertThat(result, is(expected[5][5]));
    }

    /**
     * Тест метода move с корректным ходом.
     */
    @Test
    public void whenCorrectMoveThenTrue() {
        Board board = new Board();
        Figure bishop = new Bishop("black", board, board.findCell(2, 7));
        board.addFigure(bishop);
        boolean result = board.move(bishop.position, board.findCell(6, 3));
        assertThat(result, is(true));
    }

    /**
     * Тест метода move.
     */
    @Test (expected = OccupiedWayException.class)
    public void whenOccupiedWayThenOccupiedWayException() {
        Board board = new Board();
        Figure bishop = new Bishop("black", board, board.findCell(2, 7));
        Figure bishop2 = new Bishop("white", board, board.findCell(4, 5));
        board.addFigure(bishop);
        board.addFigure(bishop2);
        boolean result = board.move(bishop.position, board.findCell(6, 3));
    }
    /**
     * Тест метода move.
     */
    @Test (expected = FigureNotFoundException.class)
    public void whenFigureNotFoundThenFigureNotFoundException() {
        Board board = new Board();
        Figure bishop = new Bishop("black", board, board.findCell(2, 7));
        Figure bishop2 = new Bishop("white", board, board.findCell(4, 5));
        board.addFigure(bishop);
        board.addFigure(bishop2);
        boolean result = board.move(board.findCell(0, 0), board.findCell(6, 3));
    }
}