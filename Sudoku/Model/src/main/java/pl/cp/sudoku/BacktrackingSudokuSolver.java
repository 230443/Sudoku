package pl.cp.sudoku;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import pl.cp.sudoku.model.SudokuBoard;

/**
 * Backtracking Sudoku Solver.
 */

class BacktrackingSudokuSolver implements SudokuSolver {

    private final Integer[] possibleValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Override
    public boolean solve(SudokuBoard board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) {
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

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(possibleValues));
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (board.set(row, col, num)) {
                if (solve(board)) {
                    return true;
                } else {
                    board.set(row, col, 0);
                }
            }
        }
        return false;
    }

}
