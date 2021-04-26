package pl.cp.sudoku.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.cp.sudoku.model.sudokuboardelement.FieldAlreadyExistException;

public class SudokuField implements Serializable {

    private int value;

    private final ArrayList<PropertyChangeListener> listeners = new ArrayList<>(3);

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

    public void setValue(int value) {

        if (value < 0 || value > 9) {
            throw new ValueOutOfScopeException("Number out of range 0-9");
        }
        int oldValue = this.value;
        try {
            this.value = value;
            notify(oldValue, value);
        } catch (FieldAlreadyExistException e) {
            this.value = oldValue;
            throw e;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener x) {
        listeners.add(x);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return listeners.toArray(new PropertyChangeListener[listeners.size()]);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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

}
