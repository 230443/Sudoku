package pl.cp.sudoku.model.sudokuboardelement;

import java.util.List;
import pl.cp.sudoku.model.SudokuField;


/**
 * Sudoku Box.
 */


public class SudokuBox extends SudokuBoardElement {

    public SudokuBox() {
        super();
    }

    public SudokuBox(List<SudokuField> sudokuFields) {
        super(sudokuFields);
    }
}