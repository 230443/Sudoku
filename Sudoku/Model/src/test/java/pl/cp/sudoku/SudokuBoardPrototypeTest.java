package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.model.SudokuBoard;
import pl.cp.sudoku.model.sudokuboardelement.SudokuBox;
import pl.cp.sudoku.model.sudokuboardelement.SudokuColumn;
import pl.cp.sudoku.model.sudokuboardelement.SudokuRow;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daniel
 */

public class SudokuBoardPrototypeTest {

    private static final Logger logger = Logger.getLogger(SudokuBoardPrototypeTest.class.getName());

    @Test
    public void PrintSudoku() {
        SudokuBoard board = SudokuBoardPrototype.getInstance();
        logger.log(Level.INFO, "Empty:\n" + board.toString());
    }

    @Test
    public void PrintSudokuEasy() {
        Difficulty d = Difficulty.EASY;
        SudokuBoard board = SudokuBoardPrototype.getInstance(d);
        logger.log(Level.INFO, d.name() + "\n" + board.toString());
        assertTrue(board.checkBoard());
    }

    @Test
    public void PrintSudokuNormal() {
        Difficulty d = Difficulty.NORMAL;
        SudokuBoard board = SudokuBoardPrototype.getInstance(d);
        logger.log(Level.INFO, d.name() + "\n" + board.toString());
        assertTrue(board.checkBoard());
    }

    @Test
    public void PrintSudokuHard() {
        Difficulty d = Difficulty.HARD;
        SudokuBoard board = SudokuBoardPrototype.getInstance(d);
        logger.log(Level.INFO, d.name() + "\n" + board.toString());
        assertTrue(board.checkBoard());
    }


}

