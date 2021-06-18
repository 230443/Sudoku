package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cp.sudoku.model.SudokuBoard;
import pl.cp.sudoku.model.sudokuboardelement.SudokuBox;
import pl.cp.sudoku.model.sudokuboardelement.SudokuColumn;
import pl.cp.sudoku.model.sudokuboardelement.SudokuRow;

import java.util.HashMap;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daniel
 */

public class SudokuBoardPrototypeTest {

    private static final Logger logger = LoggerFactory.getLogger(SudokuBoardPrototype.class);

    @Test
    public void PrintSudoku() {
        SudokuBoard board = SudokuBoardPrototype.getInstance();
        logger.info("Empty:\n" + board.toString());
    }

    @Test
    public void PrintSudokuEasy() {
        Difficulty d = Difficulty.EASY;
        SudokuBoard board = SudokuBoardPrototype.getInstance(d);
        logger.info(d.name() + "\n" + board.toString());
        assertTrue(board.checkBoard());
    }

    @Test
    public void PrintSudokuNormal() {
        Difficulty d = Difficulty.NORMAL;
        SudokuBoard board = SudokuBoardPrototype.getInstance(d);
        logger.info(d.name() + "\n" + board.toString());
        assertTrue(board.checkBoard());
    }

    @Test
    public void PrintSudokuHard() {
        Difficulty d = Difficulty.HARD;
        SudokuBoard board = SudokuBoardPrototype.getInstance(d);
        logger.info(d.name() + "\n" + board.toString());
        assertTrue(board.checkBoard());
    }


}

