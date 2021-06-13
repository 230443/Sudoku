package pl.cp.sudoku.dao;

import org.junit.jupiter.api.Test;
import pl.cp.sudoku.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {

    @Test
    public void createFileSudokuBoardDao(){

        Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("whatever.txt");
        assertTrue(dao instanceof FileSudokuBoardDao);
    }
}
