package pl.cp.sudoku.elements;

import java.io.Serializable;

/**
 * Sudoku Field.
 */




public class SudokuField implements Serializable {

    private int value;

    public SudokuField() {
        value = 0;
    }

    public int getValue() {
        return value;
    }
    
    public void setValue(int value) throws Exception {
        if (value >= 0 && value <= 9) {
            this.value = value;
        }
        else {
            throw new Exception("invalid number");
        }
    }



}
