package pl.cp.sudoku.elements;

import pl.cp.sudoku.SudokuBoard;

public class SudokuField {

    private int value;
    private SudokuRow row;
    private SudokuColumn column;
    private SudokuBox box;

    public SudokuField(SudokuRow row, SudokuColumn column, SudokuBox box) {
        this.row = row;
        this.column = column;
        this.box = box;
    }

    public int getFieldValue() {
        return value;
    }

    public void setValue(int value) throws Exception {
        this.value = value;
        throw new Exception("not implemented");
    }



}
