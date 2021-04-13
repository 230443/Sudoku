package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.elements.SudokuBox;
import pl.cp.sudoku.elements.SudokuField;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuBoardElementTest {

    @Test
    public void verifyTest() throws Exception {

        SudokuField[] field = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            field[i] = new SudokuField();
            field[i].setValue(i);
        }
        SudokuBox box1 = new SudokuBox(Arrays.asList(field));
        assertTrue(box1.verify());

        for (int i = 0; i < 9; i++) {
            field[i].setValue(1);
        }
        SudokuBox box2 = new SudokuBox(Arrays.asList(field));
        assertFalse(box2.verify());

        for (int i = 0; i < 9; i++) {
            field[i].setValue(0);
        }
        SudokuBox box3 = new SudokuBox(Arrays.asList(field));
        assertTrue(box3.verify());

    }


}
