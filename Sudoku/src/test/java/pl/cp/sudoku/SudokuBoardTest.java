package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.elements.SudokuBox;
import pl.cp.sudoku.elements.SudokuColumn;
import pl.cp.sudoku.elements.SudokuRow;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daniel
 */
public class SudokuBoardTest {

    private boolean isValidSudoku(int[][] board) {
        // init Hashmaps arrays containing hashmaps mapping values to number of times they occurred in the row, column and box
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n = board[i][j];
                int box_index = (i / 3) * 3 + j / 3;
                // the following line if there was no such key it initializes new key with 0 and adds 1
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
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        int[][] b = board.getBoard();
        assertTrue(isValidSudoku(board.getBoard()));
    }


    @Test
    public void checkTwoSudokusDifferent() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        board1.solveGame();
        SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());
        board2.solveGame();
        //we will convert arrays to strings and check if the content is not the same
        assertFalse(Arrays.deepEquals(board2.getBoard(), board1.getBoard()));
    }

    @Test
    public void checkGetBoard_returnCopy() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        int[][] b1 = board.getBoard();
        assertTrue(Arrays.deepEquals(b1, board.getBoard()));
        b1[0][0] = 0;
        //we will convert arrays to strings and check if the content is not the same
        assertFalse(Arrays.deepEquals(b1, board.getBoard()));
    }

    @Test
    public void setTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        int a = 5;
        board.set(0,0,a);
        assertEquals(a, board.get(0,0));

        assertFalse(board.set(1,0,a));

    }

    @Test
    public void getRowTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(0,0,5);
        SudokuRow row = board.getRow(0);
        assertFalse(board.set(1,0,5));

        assertTrue(row.verify());
    }

    @Test
    public void getColumnTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(0,0,5);
        SudokuColumn column = board.getColumn(0);
        assertTrue(column.verify());

        assertFalse(board.set(0,1,5));

    }

    @Test
    public void getBoxTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(0,0,5);
        SudokuBox box = board.getBox(0,0);
        assertTrue(box.verify());

        assertFalse(board.set(1,1,5));

    }



}
