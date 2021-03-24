package pl.cp.sudoku.elements;

import java.io.Serializable;

/**
 * Sudoku Field.
 */




public class SudokuField implements Serializable {

    private int value;

    public SudokuField() {
    }

    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }



}
