package pl.cp.sudoku.elements;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Sudoku board element.
 */


public abstract class SudokuBoardElement implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        sudokuFields.add((SudokuField) evt.getSource());
    }

    public SudokuBoardElement(Set<SudokuField> sudokuFields) {
        this.sudokuFields = sudokuFields;
    }

    public SudokuBoardElement(List<SudokuField> sudokuFields) {
        this.sudokuFields = new HashSet<>(sudokuFields);
    }

    public SudokuBoardElement() {
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

    private Set<SudokuField> sudokuFields = new HashSet<>(9);
}
