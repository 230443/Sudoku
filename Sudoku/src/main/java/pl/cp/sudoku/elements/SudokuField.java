package pl.cp.sudoku.elements;

import pl.cp.sudoku.SudokuBoard;

public class SudokuField {

    private int value;
    private SudokuRow row;
    private SudokuColumn column;
    private SudokuBox box;

    public SudokuField() {
        this.value = 0;
    }

    public SudokuField(SudokuRow row, SudokuColumn column, SudokuBox box) {
        this.row = row;
        this.column = column;
        this.box = box;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



}
