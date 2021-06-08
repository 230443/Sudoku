package pl.cp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import pl.cp.sudoku.model.SudokuBoard;

public class SudokuFieldProperty extends SimpleIntegerProperty {
    private final SudokuBoard board;
    private final int x;
    private final int y;

    @Override
    public void set(int i) {

        board.set(x, y, i);
        super.set(i);
    }

    public boolean setValue(Integer i) {
        super.set(i);
        return board.set(x, y, i);
    }

    @Override
    public int get() {
        super.get();
        return board.get(x, y);
    }

    /**
     * Property containing SudokuField.
     * @param board SudokuBoard to which this SudokuField belongs.
     * @param x x coordinate
     * @param y y coordinate
     */
    public SudokuFieldProperty(SudokuBoard board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }
}
