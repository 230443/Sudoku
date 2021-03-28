package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pl.cp.sudoku.elements.SudokuColumn;
import pl.cp.sudoku.elements.SudokuField;
import pl.cp.sudoku.elements.SudokuRow;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SudokuFieldTest {

    @Test
    public void set_getTest() {

        SudokuField field = new SudokuField();

        field.setValue(1);
        assertSame(1, field.getValue());
    }

    @Test
    public void getSameSudokuBoardElementTest() throws NoSuchFieldException, IllegalAccessException {

        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        //  make SudokuFields accessible
        Field fields = board.getClass().getDeclaredField("board");
        fields.setAccessible(true);
        SudokuField[][] boardFields = (SudokuField[][]) fields.get(board);

        for (int y = 0; y < 9; y++) {
            SudokuRow row = boardFields[y][0].getRow();
            SudokuColumn column = boardFields[0][y].getColumn();
            for (int x = 0; x < 9; x++) {
                assertSame(row,boardFields[y][x].getRow());
                assertSame(column,boardFields[x][y].getColumn());
            }
        }
        assertSame(boardFields[0][0].getBox(),boardFields[2][2].getBox());
        assertSame(boardFields[8][6].getBox(),boardFields[7][7].getBox());

    }

    @Test
    public void setOutOfRangeTest() {

        SudokuField field = new SudokuField();

        assertThrows(Exception.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                for (int i = -1; i < 11; i++) {
                    field.setValue(i);
                }
            }
        });

    }


}


