package pl.cp.sudoku.model.sudokuboardelement;

import java.io.Serializable;
import java.util.List;
import pl.cp.sudoku.model.SudokuField;


/**
 * Sudoku Box.
 */


public class SudokuBox extends SudokuBoardElement implements Cloneable, Serializable {

    public SudokuBox() {
        super();
    }

    public SudokuBox(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }

    @Override
    public SudokuBox clone() {
        return (SudokuBox) super.clone();
    }
}