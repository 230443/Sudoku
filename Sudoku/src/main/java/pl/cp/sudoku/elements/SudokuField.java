package pl.cp.sudoku.elements;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * Sudoku Field.
 */


public class SudokuField implements Serializable {

    private int value;

    private SudokuRow row;
    private SudokuColumn column;
    private SudokuBox box;
    private final PropertyChangeListener[] listeners = new SudokuBoardElement[3];

    {
     listeners[0] = row;
     listeners[1] = column;
     listeners[2] = box;
    }

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

    public boolean verify() {
        return row.verify() && column.verify() && box.verify();
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
