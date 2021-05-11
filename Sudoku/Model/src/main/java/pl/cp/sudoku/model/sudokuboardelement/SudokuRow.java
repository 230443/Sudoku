package pl.cp.sudoku.model.sudokuboardelement;

import java.io.Serializable;
import java.util.List;
import pl.cp.sudoku.model.SudokuField;


/**
 * Sudoku row.
 */

public class SudokuRow extends SudokuBoardElement implements Cloneable, Serializable {

    public SudokuRow() {
        super();
    }

    public SudokuRow(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        return (SudokuRow) super.clone();
    }
}
