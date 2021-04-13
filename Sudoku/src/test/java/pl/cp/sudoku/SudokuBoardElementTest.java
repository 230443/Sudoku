package pl.cp.sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.elements.SudokuBox;
import pl.cp.sudoku.elements.SudokuColumn;
import pl.cp.sudoku.elements.SudokuField;
import pl.cp.sudoku.elements.SudokuRow;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(box.verify());
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


}
