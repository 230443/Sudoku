package pl.cp.sudoku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pl.cp.sudoku.elements.SudokuField;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    @Test
    public void set_getTest() throws Exception {

        SudokuField field = new SudokuField();

        field.setValue(1);
        assertSame(1, field.getValue());

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


