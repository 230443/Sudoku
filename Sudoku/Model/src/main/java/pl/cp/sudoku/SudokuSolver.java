package pl.cp.sudoku;

import pl.cp.sudoku.model.SudokuBoard;

/**
 * Sudoku Solver.
 */

public interface SudokuSolver {
    boolean solve(SudokuBoard board);
}
