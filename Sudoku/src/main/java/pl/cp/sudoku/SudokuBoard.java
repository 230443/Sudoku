package pl.cp.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sudoku Board.
 */

public class SudokuBoard {

    public SudokuBoard() {
        fillNumbers();
    }

    private int[][] board = new int[9][9];

    public int[][] getBoard() {
        return board;
    }

    //HashSet is not random for small capacity
    private List<Integer> numbers = new ArrayList<>(9);

    private void fillNumbers() {
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    private boolean isRowSafe(int row, int guess) {
        for (int value :
                board[row]) {
            if (value == guess) {
                return false;
            }
        }
        return true;
    }

    private boolean isColSafe(int col, int guess) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == guess) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoxSafe(int row, int col, int guess) {
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + j] == guess) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSafe(int row, int col, int guess) {
        return isRowSafe(row, guess) && isColSafe(col, guess) && isBoxSafe(row, col, guess);
    }

    public boolean fillBoard() {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;

                    // We still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            return true;
        }

        for (int num : numbers) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (fillBoard()) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }
}
