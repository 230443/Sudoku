package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.elements.SudokuField;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    @Test
    public void set_getTest() throws Exception {

        SudokuField field = new SudokuField();

        field.setValue(1);
        assertSame(1, field.getValue());

    }


}


