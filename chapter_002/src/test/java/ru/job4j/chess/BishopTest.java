package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования методов класса Bishop.
 */
public class BishopTest {
    /**
     * Тест корректного хода слона.
     */
    @Test
    public void whenCorrectBishopMove() {
        Board board = new Board();
        Figure bishop = new Bishop("white", board, board.findCell(2, 7));
        board.addFigure(bishop);
        Cell[] expected = {board.findCell(3, 6), board.findCell(4, 5),
                            board.findCell(5, 4), board.findCell(6, 3)};
        Cell[] result = bishop.way(board.findCell(6, 3));
        assertThat(result, is(expected));
    }

    /**
     * Тест хода фигуры на ту же клетку, где она стоит.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenIncorrectBishopMoveThenThrowImpossibleMoveException() {
        Board board = new Board();
        Figure bishop = new Bishop("white", board, board.findCell(2, 7));
        board.addFigure(bishop);
        Cell[] result = bishop.way(board.findCell(2, 7));
    }

    /**
     * Тест некорректного хода фигуры.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenIncorrectBishopMoveThenThrowImpossibleMoveException2() {
        Board board = new Board();
        Figure bishop = new Bishop("white", board, board.findCell(2, 7));
        board.addFigure(bishop);
        Cell[] result = bishop.way(board.findCell(3, 1));
    }
}
