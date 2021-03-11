package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    public boolean isValidSudoku(int[][] board) {
        // init Hashmaps mapping values
        HashMap<Integer, Integer> [] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n = board[i][j];
                int box_index = (i / 3 ) * 3 + j / 3;
                // keep the current cell value
                rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);
                // check if this value has been already seen before
                if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1)
                    return false;
            }
        }
        return true;
    }

    @Test
    public void sudokuValidationTest() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();
        assertTrue(isValidSudoku(board.getBoard()));
    }

    @Test
    public void SolveTest() {

    }

}
