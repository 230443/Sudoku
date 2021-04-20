package pl.cp.sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.elements.SudokuBox;
import pl.cp.sudoku.elements.SudokuColumn;
import pl.cp.sudoku.elements.SudokuField;
import pl.cp.sudoku.elements.SudokuRow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public void verifyCorrectTest() throws Exception {
        for (int i = 0; i < 9; i++) {
            field[i].setValue(i);
        }
        SudokuBox box = new SudokuBox(Arrays.asList(field));
        assertTrue(box.verify());
    }

    @Test
    public void verifyIncorrectTest() throws Exception {

        for (int i = 0; i < 9; i++) {
            field[i].setValue(1);
        }
        SudokuBox box = new SudokuBox(Arrays.asList(field));
        assertFalse(box.verify());
    }

    @Test
    public void verifyEmptyTest() throws Exception {

        for (int i = 0; i < 9; i++) {
            field[i].setValue(0);
        }
        SudokuBox box = new SudokuBox(Arrays.asList(field));
        SudokuBox box2 = new SudokuBox();
        assertTrue(box.verify());
        assertTrue(box2.verify());
    }

    @Test
    public void addFieldTest() throws Exception {

        SudokuRow row = new SudokuRow();
        SudokuColumn column = new SudokuColumn();
        SudokuBox box = new SudokuBox();

        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField(row, column, box);
            row.addField(fields[i]);
            column.addField(fields[i]);
            box.addField(fields[i]);
        }
        assertTrue(Arrays.equals(fields[0].getPropertyChangeListeners(), fields[5].getPropertyChangeListeners()));
    }

    @Test
    public void notifyTest() throws Exception {

        SudokuRow row = new SudokuRow();
        SudokuColumn column = new SudokuColumn();
        SudokuBox box = new SudokuBox();

        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField(row, column, box);
        }

        for (int i = 0; i < 9; i++) {
            fields[i].setValue(1);
        }

        for (int i = 0; i < 9; i++) {
            assertFalse(fields[i].verify());
        }
    }

    //@Test
    public void hashSetAddTheSameElement() throws Exception{

        Set<SudokuField> sudokuFields = new HashSet<>(9);

        sudokuFields.add(field[0]);
        assertTrue(sudokuFields.contains(field[0]));

        field[0].setValue(7);
        sudokuFields.add(field[0]);
        assertEquals(1, sudokuFields.size());

        for (SudokuField sf :
                sudokuFields) {
            assertEquals(7, sf.getValue());
        }
    }

}
