package pl.cp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import pl.cp.sudoku.model.SudokuBoard;

public class SudokuFieldProperty extends SimpleIntegerProperty {
    private final SudokuBoardStringProperty board;
    private final int x;
    private final int y;

    @Override
    public void set(int i) {
        board.getBoard().set(x, y, i);
        System.out.println(board.getBoard());
        super.set(i);
        board.fireValueChangedEvent();
    }

    @Override
    public int get() {
        super.get();
        return board.getBoard().get(x, y);
    }

    public SudokuFieldProperty(SudokuBoardStringProperty board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
    }
}
