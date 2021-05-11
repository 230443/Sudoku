package pl.cp.sudoku.model.sudokuboardelement;

import java.io.Serializable;
import java.util.List;
import pl.cp.sudoku.model.SudokuField;


/**
 * Sudoku Column.
 */

public class SudokuColumn extends SudokuBoardElement implements Cloneable, Serializable {

    public SudokuColumn() {
        super();
    }

    public SudokuColumn(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuColumn clone() {
        return (SudokuColumn) super.clone();
    }
}
