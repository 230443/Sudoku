package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.model.*;
import pl.cp.sudoku.model.sudokuboardelement.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardElementTest {

    SudokuField[] field;

    public SudokuBoardElementTest() {
        field = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            field[i] = new SudokuField();
        }
    }


    @Test
    public void verifyCorrectTest() {
        for (int i = 0; i < 9; i++) {
            field[i].setValue(i);
        }
        SudokuBox box = new SudokuBox(Arrays.asList(field));
        assertTrue(box.verify());
    }

    @Test
    public void verifyIncorrectTest() {

        for (int i = 0; i < 9; i++) {
            field[i].setValue(1);
        }
        SudokuBox box = new SudokuBox(Arrays.asList(field));
        assertFalse(box.verify());
    }

    @Test
    public void verifyEmptyTest() {

        for (int i = 0; i < 9; i++) {
            field[i].setValue(0);
        }
        SudokuBox box = new SudokuBox(Arrays.asList(field));
        SudokuBox box2 = new SudokuBox();
        assertTrue(box.verify());
        assertTrue(box2.verify());
    }

    @Test
    public void addFieldTest() {

        SudokuRow row = new SudokuRow();
        SudokuColumn column = new SudokuColumn();
        SudokuBox box = new SudokuBox();

        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            row.addField(fields[i]);
            column.addField(fields[i]);
            box.addField(fields[i]);
        }
        assertTrue(Arrays.equals(fields[0].getPropertyChangeListeners(), fields[5].getPropertyChangeListeners()));
    }

    @Test
    public void notifyTest() {

        SudokuRow row = new SudokuRow();
        SudokuColumn column = new SudokuColumn();
        SudokuBox box = new SudokuBox();

        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            row.addField(fields[i]);
            box.addField(fields[i]);
            column.addField(fields[i]);
        }
        fields[0].setValue(1);
        assertThrows(FieldAlreadyExistException.class, () -> fields[1].setValue(1));
    }

    @Test
    public void hashCodeTestTrue() {
        SudokuBoardElement element1 = new SudokuRow();
        SudokuBoardElement element2 = new SudokuRow();
        assertEquals(element2.hashCode(), element1.hashCode());

    }

    @Test
    public void hashCodeTestFalse() {
        SudokuBoardElement element1 = new SudokuRow();
        SudokuBoardElement element2 = new SudokuRow();
        element2.addField(new SudokuField());
        assertNotEquals(element2.hashCode(), element1.hashCode());

    }

    @Test
    public void toStringTest() {
        SudokuBoardElement element1 = new SudokuRow();
        SudokuBoardElement element2 = new SudokuRow();
        element2.addField(new SudokuField());
        assertNotEquals(element1.toString(), element2.toString());


    }

    @Test
    public void toStringTest2() {
        SudokuBoardElement element1 = new SudokuRow();

        assertEquals(element1.toString(), element1.toString());

    }

    @Test
    public void EqualityTest() {
        SudokuBoardElement element1 = new SudokuRow();
        SudokuBoardElement element2 = new SudokuRow();
        assertEquals(element1, element1);
        assertEquals(element2, element1);

    }

    @Test
    public void EqualsDifferentClassTest() {
        SudokuBoardElement element1 = new SudokuRow();
        SudokuBoardElement element2 = new SudokuColumn();
        assertNotEquals(element2, element1);
    }

    @Test
    public void EqualsNullTest() {
        SudokuBoardElement element1 = new SudokuRow();
        SudokuBoardElement element2 = new SudokuRow();
        element2.addField(new SudokuField());
        assertNotEquals(element1, null);
        assertNotEquals(element1, element2);
    }

}
