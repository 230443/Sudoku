package pl.cp.sudoku.model.sudokuboardelement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import pl.cp.sudoku.exceptions.FieldAlreadyExistException;
import pl.cp.sudoku.model.sudokufield.SudokuField;

public abstract class SudokuBoardElement implements PropertyChangeListener, Cloneable {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!verify()) {
            throw new FieldAlreadyExistException(FieldAlreadyExistException.AlreadyExist);
        }
    }

    public SudokuBoardElement(List<SudokuField> sudokuFields) {
        this.sudokuFields = sudokuFields;
    }

    public SudokuBoardElement() {
    }

    /**
     * Verify correctness of this element.
     * @return true, if current sudoku combination is possible, false otherwise.
     */
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

    /**
     * Adds new SudokuField to this SudokuBoardElement.
     * @param field SudokuField to be added.
     */
    public void addField(SudokuField field) {
        sudokuFields.add(field);
        field.addPropertyChangeListener(this);

    }

    @Override
    public String toString() {
        return "SudokuBoardElement{"
                + "sudokuFields=" + sudokuFields
                + '}';
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(sudokuFields).toHashCode();
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

    @Override
    protected SudokuBoardElement clone() throws CloneNotSupportedException {

        SudokuBoardElement result = (SudokuBoardElement) super.clone();

        result.sudokuFields = new ArrayList<>(9);
        for (var field : sudokuFields) {
            result.addField(field.clone());
        }
        return result;
    }



    private List<SudokuField> sudokuFields = new ArrayList<>(9);
}
