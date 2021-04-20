package pl.cp.sudoku.model.sudokuboardelement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.cp.sudoku.model.SudokuField;

/**
 * Sudoku board element.
 */


public abstract class SudokuBoardElement implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SudokuField newField = (SudokuField) evt.getSource();
        if (sudokuFields.contains(newField)) {
            sudokuFields.remove(newField);
        }
        sudokuFields.add(newField);


    }

    public SudokuBoardElement(List<SudokuField> sudokuFields) {
        this.sudokuFields = sudokuFields;
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

    public void addField(SudokuField field) {
        sudokuFields.add(field);
        field.addPropertyChangeListener(this);

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sudokuFields);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoardElement that = (SudokuBoardElement) o;

        return new EqualsBuilder().append(sudokuFields, that.sudokuFields).isEquals();
    }

    private List<SudokuField> sudokuFields = new ArrayList<>(9);
}
