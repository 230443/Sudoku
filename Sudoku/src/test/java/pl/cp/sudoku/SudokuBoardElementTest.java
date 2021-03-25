package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.elements.SudokuBox;
import pl.cp.sudoku.elements.SudokuField;
import pl.cp.sudoku.elements.SudokuRow;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardElementTest {

    @Test
    public void set_getTest() throws Exception {

        SudokuField[] field = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            field[i] = new SudokuField();
            field[i].setValue(i);
        }
        SudokuBox box1 = new SudokuBox(field);
        assertTrue(box1.verify());

        for (int i = 0; i < 9; i++) {
            field[i].setValue(1);
        }
        SudokuBox box2 = new SudokuBox(field);
        assertFalse(box2.verify());

    }


}
