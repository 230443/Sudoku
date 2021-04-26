package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pl.cp.sudoku.model.SudokuBoard;
import pl.cp.sudoku.model.sudokuboardelement.SudokuColumn;
import pl.cp.sudoku.model.SudokuField;
import pl.cp.sudoku.model.sudokuboardelement.SudokuRow;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    @Test
    public void set_getTest() throws Exception {

        SudokuField field = new SudokuField();

        field.setValue(1);
        assertSame(1, field.getValue());
    }

    /*
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
                assertSame(row, boardFields[y][x].getRow());
                assertSame(column, boardFields[x][y].getColumn());
            }
        }
        assertSame(boardFields[0][0].getBox(), boardFields[2][2].getBox());
        assertSame(boardFields[8][6].getBox(), boardFields[7][7].getBox());

    }
*/
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

    @Test
    public void notifyNullListener() throws Exception {

        SudokuField field = new SudokuField();

        SudokuRow row = null;
        field.addPropertyChangeListener(row);
        field.setValue(7);
    }

    @Test
    public void EqualityTest() throws Exception {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        assertTrue(field1.equals(field2));

    }

    @Test
    public void InEqualityTest() throws Exception {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setValue(2);
        assertFalse(field1.equals(field2));

    }
    @Test
    public void InEqualityTest2() throws Exception {

        SudokuField field1 = new SudokuField();


        assertFalse(field1.equals(3));

    }

    @Test
    public void hashCodeTest() throws Exception {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setValue(3);
        assertFalse(field1.hashCode()==field2.hashCode());

    }

    @Test
    public void ToStringTestFalse() throws Exception {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setValue(3);
        assertFalse(field1.toString()==field2.toString());

    }

    @Test
    public void ToStringTestFalse2() throws Exception {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();

        assertFalse(field1.toString()==field2.toString());

    }

}


