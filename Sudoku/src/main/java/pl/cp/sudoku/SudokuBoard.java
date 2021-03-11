package pl.cp.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sudoku Board.
 */

public class SudokuBoard {
    private int[][] board = new int[9][9];

    //default access modifier used for testing

    //HashSet is not random for small capacity
    //Set<Integer> numbers = new HashSet<>(9);

    List<Integer> numbers = new ArrayList<>(9);

    void fillNumbers() {
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    boolean isRowSafe(int row, int guess) {
        for (int value :
                board[row]) {
            if (value == guess) {
                return false;
            }
        }
        return true;
    }

    boolean isColSafe(int col, int guess) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == guess) {
                return false;
            }
        }
        return true;
    }

    boolean isBoxSafe(int row, int col, int guess) {
        row = (row % 3) * 3;
        col = (col % 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + i] == guess) {
                    return false;
                }
            }
        }
        return true;
    }


    public void fillBoard() {

    }
}
