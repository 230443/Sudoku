package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.model.SudokuBoard;
import pl.cp.sudoku.model.sudokuboardelement.SudokuBox;
import pl.cp.sudoku.model.sudokuboardelement.SudokuColumn;
import pl.cp.sudoku.model.sudokuboardelement.SudokuRow;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daniel
 */

public class SudokuBoardPrototypeTest {

    @Test
    public void PrintSudoku() {
        SudokuBoard board = SudokuBoardPrototype.getInstance();
        System.out.print(board.toString() + "\n");
    }

    @Test
    public void PrintSudokuEasy() {
        SudokuBoard board = SudokuBoardPrototype.getInstance(Difficulty.EASY);
        System.out.print(board.toString() + "\n");
        assertTrue(board.checkBoard());
    }

    @Test
    public void PrintSudokuNormal() {
        SudokuBoard board = SudokuBoardPrototype.getInstance(Difficulty.NORMAL);
        System.out.print(board.toString() + "\n");
        assertTrue(board.checkBoard());
    }

    @Test
    public void PrintSudokuHard() {
        SudokuBoard board = SudokuBoardPrototype.getInstance(Difficulty.HARD);
        System.out.print(board.toString());
        assertTrue(board.checkBoard());
    }


}

