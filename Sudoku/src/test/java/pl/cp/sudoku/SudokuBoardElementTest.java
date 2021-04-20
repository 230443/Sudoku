package pl.cp.sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.elements.*;

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


    @Test
    public void hashCodeTestTrue() throws Exception{
        SudokuBoardElement element1=new SudokuRow();
        SudokuBoardElement element2=new SudokuRow();
        assertTrue(element1.hashCode()==element2.hashCode());

    }

    @Test
    public void hashCodeTestFalse() throws Exception{
        SudokuBoardElement element1=new SudokuRow();
        SudokuBoardElement element2=new SudokuRow();
        element2.addField(new SudokuField());
        assertFalse(element1.hashCode()==element2.hashCode());

    }

    @Test
    public void toStringTest() throws Exception{
        SudokuBoardElement element1=new SudokuRow();
        SudokuBoardElement element2=new SudokuRow();
        element2.addField(new SudokuField());
        assertFalse(element1.toString()==element2.toString());

    }

    @Test
    public void toStringTest2() throws Exception{
        SudokuBoardElement element1=new SudokuRow();

        assertFalse(element1.toString()==element1.toString());

    }

    @Test
    public void EqualityTest() throws Exception{
        SudokuBoardElement element1=new SudokuRow();
        SudokuBoardElement element2=new SudokuRow();
        assertTrue(element1.equals(element2));

    }

    @Test
    public void InEqualityTest() throws Exception{
        SudokuBoardElement element1=new SudokuRow();
        assertFalse(element1.equals(2));

    }

    @Test
    public void InEqualityTest2() throws Exception{
        SudokuBoardElement element1=new SudokuRow();
        SudokuBoardElement element2=new SudokuRow();
        element2.addField(new SudokuField());
        assertFalse(element1.equals(element2));

    }


}
