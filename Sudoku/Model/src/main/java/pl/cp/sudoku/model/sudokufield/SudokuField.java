package pl.cp.sudoku.model.sudokufield;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import pl.cp.sudoku.model.sudokuboardelement.FieldAlreadyExistException;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    private int value;

    private transient ArrayList<PropertyChangeListener> listeners = new ArrayList<>(3);

    private void notify(int oldValue, int newValue) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "value", oldValue, newValue);
        for (PropertyChangeListener listener :
                listeners) {
            if (listener == null) {
                continue;
            }
            listener.propertyChange(event);
        }
    }

    public SudokuField() {
        value = 0;
    }

    public int getValue() {
        return value;
    }


    /**
     * Sets value of this SudokuField.
     * @param value int value to be set.
     */
    public void setValue(int value) {

        if (value < 0 || value > 9) {
            throw new ValueOutOfScopeException("Number out of range 0-9");
        }
        int oldValue = this.value;
        try {
            this.value = value;
            notify(oldValue, value);
        } catch (FieldAlreadyExistException e) {
            //this.value = oldValue;
            throw e;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener x) {
        listeners.add(x);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return listeners.toArray(new PropertyChangeListener[0]);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    // commons.lang3 version
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(this.value)
                //.append(this.listeners)
                .toHashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public SudokuField clone() {
        SudokuField result;
        try {
            result = (SudokuField) super.clone();
            // Should listeners be cleaned?
            result.listeners = new ArrayList<>(3);
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
        return result;
    }

    @Override
    public int compareTo(SudokuField o) {
        if(o==null) throw new NullPointerException("arg is null");
        return Integer.compare(this.value, o.getValue());
    }


}
