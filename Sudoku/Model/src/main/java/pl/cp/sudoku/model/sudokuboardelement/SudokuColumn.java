package pl.cp.sudoku.model.sudokuboardelement;

import java.util.List;
import pl.cp.sudoku.model.SudokuField;


/**
 * Sudoku Column.
 */

public class SudokuColumn extends SudokuBoardElement {

    public SudokuColumn() {
        super();
    }

    public SudokuColumn(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }
}
