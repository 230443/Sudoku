package pl.cp.sudoku.elements;

public class SudokuField {

    private int value;

    public int getFieldValue() {
        return value;
    }

    public void setValue(int value) throws Exception {
        this.value = value;
        throw new Exception("not implemented");
    }
}
