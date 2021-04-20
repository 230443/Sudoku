package pl.cp.sudoku.model.sudokuboardelement;

import java.util.List;
import pl.cp.sudoku.model.SudokuField;


/**
 *  Sudoku row.
 */

public class SudokuRow extends SudokuBoardElement {

    public SudokuRow() {
        super();
    }

    public SudokuRow(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }
}
