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

    @Test
    public void SolveTest() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();
        for (int i = 0; i<9 ; i++)
        {
            for (int j = 0; j<9 ; j++)
            {
                System.out.print(board.board[i][j]);
            }
            System.out.println();
        }
    }

}
