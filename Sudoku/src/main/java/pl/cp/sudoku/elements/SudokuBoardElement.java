package pl.cp.sudoku.elements;

/**
 * Sudoku board element.
 */


public abstract class SudokuBoardElement {

    public SudokuBoardElement(SudokuField[] fields) {
        this.sudokuFields = fields;
    }

    public boolean verify() {

        int[] fieldCounters = new int[10];

        for (SudokuField field :
                sudokuFields) {

            //there can be more than one 0
            if (++fieldCounters[field.getValue()] > 1 && field.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    protected SudokuField[] sudokuFields = new SudokuField[9];
}
