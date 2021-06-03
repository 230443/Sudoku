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

    @Override
    public int get() {
        super.get();
        return board.get(x, y);
    }

    public SudokuFieldProperty(SudokuBoard board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }
}
