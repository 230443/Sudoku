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

    private void notify(int oldValue) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "value", oldValue, this.value);
        for (PropertyChangeListener listener :
                listeners) {
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

    public void setValue(int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
        } else {
            //throw new Exception("invalid number");
        }
    }

    public SudokuRow getRow() {
        return row;
    }

    public void setRow(SudokuRow row) {
        this.row = row;
    }

    public SudokuColumn getColumn() {
        return column;
    }

    public void setColumn(SudokuColumn column) {
        this.column = column;
    }

    public SudokuBox getBox() {
        return box;
    }

    public void setBox(SudokuBox box) {
        this.box = box;
    }
}
