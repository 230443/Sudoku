package pl.cp.sudoku.elements;

public abstract class SudokuBoardElement {

    public boolean verify() {

        int[] fieldCounters = new int[9];

        for (SudokuField field :
                sudokuFields) {

            if(++fieldCounters[field.getFieldValue()] > 1) {
                return false;
            }
        }
        return true;
    }

    private SudokuField[] sudokuFields;
}
