package pl.cp;

import javafx.beans.property.SimpleStringProperty;
import pl.cp.sudoku.model.SudokuBoard;

public class SudokuBoardStringProperty extends SimpleStringProperty {
    private SudokuBoard board;

    @Override
    public String get() {
        super.get();
        return board.toString();
    }

    public SudokuBoardStringProperty(SudokuBoard board) {
        this.board = board;
    }

    @Override
    public void fireValueChangedEvent() {
        super.fireValueChangedEvent();
    }

    public SudokuBoard getBoard() {
        return board;
    }
}
