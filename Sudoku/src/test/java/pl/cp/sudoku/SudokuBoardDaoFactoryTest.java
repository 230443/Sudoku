package pl.cp.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.model.*;
import pl.cp.sudoku.model.sudokuboardelement.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {

    @Test
    public void createFileSudokuBoardDao(){

        Dao dao=new SudokuBoardDaoFactory().getFileDao("whatever.txt");
        assertTrue(dao instanceof FileSudokuBoardDao);
    }
}
