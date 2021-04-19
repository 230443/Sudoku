package pl.cp.sudoku.elements;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Sudoku board element.
 */


public abstract class SudokuBoardElement implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        sudokuFields.add((SudokuField) evt.getSource());
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
    public boolean equals(final Object obj) {
        if (obj instanceof SudokuBoardElement) {
            final SudokuBoardElement other = (SudokuBoardElement) obj;
            return new EqualsBuilder()
                    .append(sudokuFields, other.sudokuFields)
                    .isEquals();
        } else {
            return false;
        }
    }


    private Set<SudokuField> sudokuFields = new HashSet<>(9);
}
