package pl.cp.sudoku.elements;

import java.util.List;
import java.util.Set;

/**
 *  Sudoku row.
 */

public class SudokuRow extends SudokuBoardElement {
    public SudokuRow() {
        super();
    }

    public SudokuRow(Set<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    public SudokuRow(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }
}
