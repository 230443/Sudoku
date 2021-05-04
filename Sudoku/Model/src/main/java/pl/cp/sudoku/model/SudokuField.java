package pl.cp.sudoku.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
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
            this.value = oldValue;
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

//    @Override
//    public int compareTo(Object o)  {
//        if (this == o) {
//            return 1;
//        }
//
//        if (o == null || getClass() != o.getClass()) {
//            return 0;
//        }
//
//        SudokuField that = (SudokuField) o;
//
//        if (value == that.value) return 1;
//        else return 0;
//    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        SudokuField result = (SudokuField) super.clone();
        result.listeners = new ArrayList<>(3);
        return result;
    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */

    @Override
    public int compareTo(SudokuField o) {
        return Integer.compare(this.value, o.getValue());
    }


}
