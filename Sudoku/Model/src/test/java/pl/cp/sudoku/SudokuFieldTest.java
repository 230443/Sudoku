package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cp.sudoku.model.sudokufield.SudokuField;
import pl.cp.sudoku.model.sudokuboardelement.SudokuBox;
import pl.cp.sudoku.model.sudokuboardelement.SudokuColumn;
import pl.cp.sudoku.model.sudokuboardelement.SudokuRow;
import pl.cp.sudoku.model.sudokufield.UnmodifiableSudokuField;
import pl.cp.sudoku.exceptions.UnmodifiableSudokuFieldException;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    private static final Logger logger = LoggerFactory.getLogger(SudokuBoardPrototype.class);

    @Test
    public void set_getTest() {

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

        assertThrows(Exception.class, () -> {
            for (int i = -1; i < 11; i++) {
                field.setValue(i);
            }
        });
    }

    @Test
    public void notifyNullListener() {

        SudokuField field = new SudokuField();

        SudokuRow row = null;
        field.addPropertyChangeListener(row);
        field.setValue(7);
    }

    @Test
    public void EqualityTest() {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        assertEquals(field1, field1);
        assertEquals(field2, field1);

    }

    @Test
    public void InEqualityTest() {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setValue(2);

        assertNotEquals(field2, field1);
    }

    @Test
    public void EqualsDifferentClassTest() {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = null;
        SudokuRow row = new SudokuRow();
        assertFalse(field1.equals(row));
        assertFalse(field1.equals(null));
        assertFalse(field1.equals(field2));
    }

    @Test
    public void DifferentHashCodeTest() {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setValue(3);

        assertNotEquals(field2.hashCode(), field1.hashCode());
    }

    @Test
    public void SameHashCodeTest() {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();

        assertEquals(field2.hashCode(), field1.hashCode());
    }

    @Test
    public void ToStringTestFalse() {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field2.setValue(3);

        assertNotEquals(field1.toString(), field2.toString());
    }

    @Test
    public void ToStringTestFalse2() {

        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();

        assertEquals(field1.toString(), field2.toString());
    }

    @Test
    public void cloneValueTest() throws CloneNotSupportedException {

        SudokuField f1 = new SudokuField();
        f1.setValue(1);
        SudokuField f2 = f1.clone();

        assertNotSame(f1, f2);
        assertEquals(f1, f2);

        f2.setValue(8);

        assertNotEquals(f1, f2);

    }

    @Test
    public void cloneListenersTest() {

        SudokuRow row = new SudokuRow();
        SudokuColumn column = new SudokuColumn();
        SudokuBox box = new SudokuBox();

        SudokuField f1 = new SudokuField();

        row.addField(f1);
        column.addField(f1);
        box.addField(f1);

        SudokuField f2 = f1.clone();

        //Assert not throws
        f1.setValue(8);
        f2.setValue(8);

    }

    @Test
    public void compareToTest() throws CloneNotSupportedException {

        SudokuField f1 = new SudokuField();
        SudokuField f2 = new SudokuField();
        SudokuField f3 = new SudokuField();


        f1.setValue(1);
        f2.setValue(2);
        f3.setValue(3);

        assertEquals(-1, f1.compareTo(f2));
        assertEquals(-1, f2.compareTo(f3));
        assertEquals(-1, f1.compareTo(f3));
        assertEquals(1, f2.compareTo(f1));
        assertEquals(1, f3.compareTo(f1));
        assertEquals(1, f3.compareTo(f2));

        f2.setValue(1);
        f3.setValue(1);


        assertEquals(0, f1.compareTo(f2));
        assertEquals(0, f1.compareTo(f3));
        assertEquals(0, f2.compareTo(f3));
        assertThrows(NullPointerException.class, () -> f1.compareTo(null));

    }

    @Test
    public void tryModifyUnmodifiableSudokuField() {

        SudokuField f1 = new SudokuField();
        f1.setValue(1);

        UnmodifiableSudokuField field = new UnmodifiableSudokuField(f1);

        try {
            field.setValue(2);
            fail();
        } catch (UnmodifiableSudokuFieldException e) {
            logger.error(null, e);
        }
    }

}


