package pl.cp.sudoku;

import java.io.File;
import pl.cp.sudoku.model.SudokuBoard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class FileSudokuBoardDaoTest {


    private SudokuBoard writeSolvedBoard(String fileName) {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao(fileName);
        fileDao.write(sudokuBoard);
        return sudokuBoard;
    }

    @Test
    public void writeToWrongFileTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao("&?/|.txt");
        fileDao.write(sudokuBoard);
    }

    @Test
    public void WriteAndReadBoardTest() {

        var board = writeSolvedBoard("solvedSudoku.dat");
        Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("solvedSudoku.dat");
        SudokuBoard readBoard = fileSudokuBoardDao.read();
        assertEquals(board, readBoard);
    }

    @Test
    public void readNonExistingFileTest() {
        Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("notFound");
        var readBoard = fileSudokuBoardDao.read();
        assertNull(readBoard);
    }

    @Test
    public void readBadFileTest() {
        Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("faulty.txt");
        SudokuBoard sudokuBoard = fileSudokuBoardDao.read();
        assertNull(sudokuBoard);
    }

    @Test
    public void AutoClosableTest() {
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("tryTest.dat")) {

            SudokuBoard b1 = new SudokuBoard(new BacktrackingSudokuSolver());
            b1.solveGame();
            dao.write(b1);
            var b2 =  dao.read();

            assertEquals(b1,b2);
        }
    }

    @AfterAll
    public static void clean() {
        File f1 = new File("tryTest.dat");
        File f2 = new File("solvedSudoku.dat");
        f1.delete();
        f2.delete();
    }

}
