package pl.cp.sudoku.elements;

public abstract class SudokuBoardElement {

    public SudokuBoardElement(SudokuField[] fields) {
        this.sudokuFields = fields;
    }

    public boolean verify() {

        int[] fieldCounters = new int[9];

        for (SudokuField field :
                sudokuFields) {

            if (++fieldCounters[field.getValue()] > 1) {
                return false;
            }
        }
        return true;
    }

    protected SudokuField[] sudokuFields;
}
