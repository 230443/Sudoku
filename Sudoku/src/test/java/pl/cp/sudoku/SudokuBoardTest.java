package pl.cp.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author daniel
 */
public class SudokuBoardTest {

    public SudokuBoardTest() {
    }

    /**
     * Test of append method, of class StudentArrayList.
     */
    @Test
    public void fillBoardTest() {

    }
    @Test
    public void shuffleTest() {
        SudokuBoard board = new SudokuBoard();
        board.fillNumbers();
        System.out.println(board.numbers);
        Collections.shuffle(board.numbers);
        System.out.println(board.numbers);
    }

}
