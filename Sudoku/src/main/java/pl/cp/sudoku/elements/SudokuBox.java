package pl.cp.sudoku.elements;


import java.util.List;
import java.util.Set;

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