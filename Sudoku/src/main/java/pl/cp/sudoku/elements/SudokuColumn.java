package pl.cp.sudoku.elements;

import java.util.List;
import java.util.Set;

/**
 * Sudoku Column.
 */

public class SudokuColumn extends SudokuBoardElement {
    public SudokuColumn() {
        super();
    }

    public SudokuColumn(Set<SudokuField> sudokuFields) {
        super(sudokuFields);
    }


    public SudokuColumn(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }


}
