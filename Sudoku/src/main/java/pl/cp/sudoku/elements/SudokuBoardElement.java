package pl.cp.sudoku.elements;

/**
 * Sudoku board element.
 */



public abstract class SudokuBoardElement {

    public SudokuBoardElement(SudokuField[] fields) {
        //for (int i = 0; i < 9; i++) {
//
        //    this.sudokuFields[i] = new SudokuField();
        //    try {
        //        this.sudokuFields[i].setValue(fields[i].getValue());
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //}
        this.sudokuFields = fields;
    }

    public boolean verify() {

        int[] fieldCounters = new int[10];

        for (SudokuField field :
                sudokuFields) {

            if (++fieldCounters[field.getValue()] > 1 && field.getValue()!=0) { //there can be more than one 0
                return false;
            }
        }
        return true;
    }

    protected SudokuField[] sudokuFields = new SudokuField[9];
}
