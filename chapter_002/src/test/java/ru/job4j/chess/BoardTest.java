package ru.job4j.chess;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void whenThenTestedBoardClassAddFigures() {
        Bishop bishop1 = new Bishop(4, 4);
        Bishop bishop2 = new Bishop(3, 3);
        Board board = new Board();
        board.figureTouch(bishop1);
        board.figureTouch(bishop2);
        Figure[] result = board.getFigures();
        assertThat(8, is(result[0].getPosition().getHorizontal() + result[0].getPosition().getHorizontal()));
        assertThat(6, is(result[1].getPosition().getHorizontal() + result[1].getPosition().getHorizontal()));
        assertThat(null, is(result[2]));
    }
    @Test
    public void whenThenAddFiguresInBoard() {
        Bishop bishop1 = new Bishop(4, 4);
        Bishop bishop2 = new Bishop(3, 3);
        Board board = new Board();
        board.figureTouch(bishop1);
        board.figureTouch(bishop2);
        board.figureSetInBoard();
        Figure[][] figures = board.getBoard();
        assertThat(bishop1, is(figures[4][4]));
        assertThat(bishop2, is(figures[3][3]));
    }
    @Test
    public void whenGiveCellThenMovedFigureInBoard() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Bishop bishop1 = new Bishop(4, 4);
        Bishop bishop2 = new Bishop(3, 3);
        Cell test1 = new Cell(4, 4);
        Cell test2 = new Cell(7, 7);
        Board board = new Board();
        board.figureTouch(bishop1);
        board.figureTouch(bishop2);
        board.figureSetInBoard();
        assertThat(true, is(board.move(test1, test2)));
    }
    @Test(expected = FigureNotFoundException.class)
    public void whenGiveCellThenExceptedNoFigure() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Board board = new Board();
        Cell test = new Cell(4, 4);
        Cell test1 = new Cell(5, 5);
        board.move(test, test1);
    }
    @Test(expected = ImpossibleMoveException.class)
    public void whenGiveCellSourceAndDistThenExceptedImpossibleMove() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Board board = new Board();
        Bishop bishop = new Bishop(4, 4);
        board.figureTouch(bishop);
        board.figureSetInBoard();
        Cell test = new Cell(4, 4);
        Cell test1 = new Cell(3, 2);
        board.move(test, test1);
    }
    @Test(expected = OccupiedWayException.class)
    public void whenGiveCellSourceAndDistThenExceptedOccupiedWay() throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Board board = new Board();
        Bishop bishop1 = new Bishop(4, 4);
        Bishop bishop2 = new Bishop(5, 5);
        board.figureTouch(bishop1);
        board.figureTouch(bishop2);
        board.figureSetInBoard();
        Cell test = new Cell(4, 4);
        Cell test1 = new Cell(6, 6);
        board.move(test, test1);
    }
}