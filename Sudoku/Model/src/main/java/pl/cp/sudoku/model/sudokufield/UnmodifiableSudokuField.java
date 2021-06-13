package pl.cp.sudoku.model.sudokufield;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class UnmodifiableSudokuField extends SudokuField implements Serializable {

    private final SudokuField sudokuField;

    public UnmodifiableSudokuField(SudokuField sudokuField) {
        super();
        this.sudokuField = sudokuField;
    }

    @Override
    public int getValue() {
        return sudokuField.getValue();
    }

    @Override
    public void setValue(int value) {
        throw new UnmodifiableSudokuFieldException(
                UnmodifiableSudokuFieldException.UNMODIFIABLE_VALUE);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener x) {
        sudokuField.addPropertyChangeListener(x);
    }

    @Override
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return sudokuField.getPropertyChangeListeners();
    }
}


