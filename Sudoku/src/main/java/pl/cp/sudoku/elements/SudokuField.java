package pl.cp.sudoku.elements;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Sudoku Field.
 */


public class SudokuField implements Serializable {

    private int value;

    private SudokuRow row;
    private SudokuColumn column;
    private SudokuBox box;
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


    public SudokuField(SudokuRow row, SudokuColumn column, SudokuBox box) {
        this.row = row;
        this.column = column;
        this.box = box;

        listeners.add(row);
        listeners.add(column);
        listeners.add(box);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) throws Exception {
        if (value >= 0 && value <= 9) {
            notify(this.value, value);
            this.value = value;
        } else {
            throw new Exception("Number out of range 0-9");
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener x) {
        listeners.add(x);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return listeners.toArray(new PropertyChangeListener[listeners.size()]);
    }

    public boolean verify() {
        return row.verify() && column.verify() && box.verify();
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /*

    // commons.lang3 version
        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 31)
                    .append(this.value)
                    .append(this.listeners)
                    .toHashCode();
        }
    */

    // java 7 version
    @Override
    public int hashCode() {
        return Objects.hash(value, getPropertyChangeListeners());
    }


    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof SudokuField) {
            final SudokuField other = (SudokuField) obj;
            return new EqualsBuilder()
                    .append(value, other.value)
                    .append(listeners, other.listeners)
                    .isEquals();
        } else {
            return false;
        }
    }


    public SudokuRow getRow() {
        return row;
    }

    public SudokuColumn getColumn() {
        return column;
    }

    public SudokuBox getBox() {
        return box;
    }
}
