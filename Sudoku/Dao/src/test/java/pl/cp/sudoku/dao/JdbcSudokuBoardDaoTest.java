package pl.cp.sudoku.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.BacktrackingSudokuSolver;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class JdbcSudokuBoardDaoTest {




    private SudokuBoard writeSolvedBoard(String name) {
        SudokuBoard sudokuBoard = SudokuBoardPrototype.getInstance(Difficulty.HARD);
        try ( Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao(name)) {
            dao.write(sudokuBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sudokuBoard;
    }

    @Test
    public void checkConnection() throws SQLException {
        Connection connection = DbConnector.connect();
        assertNotNull(connection);
        connection.close();
    }

    @Disabled
    @Test
    public void writeToWrongFileTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getJdbcDao("&?/|.txt");
        fileDao.write(sudokuBoard);
    }


    @Test
    public void WriteAndReadBoardTest() {

        SudokuBoard b1 = writeSolvedBoard("solvedBoard");
        SudokuBoard b2 = null;
        try ( Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("solvedBoard")) {
            b2 = dao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(b1, b2);
    }

    @Disabled
    @Test
    public void ReadandSolveBoardTest() {

        var board = writeSolvedBoard("solvedSudoku.dat");
        Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getJdbcDao("solvedSudoku.dat");
        SudokuBoard readBoard = fileSudokuBoardDao.read();
        readBoard.solveGame();
    }

    @Disabled
    @Test
    public void readNonExistingFileTest() {
        Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getJdbcDao("notFound");
        var readBoard = fileSudokuBoardDao.read();
        assertNull(readBoard);
    }

    @Disabled
    @Test
    public void readBadFileTest() {
        Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getJdbcDao("faulty.txt");
        SudokuBoard sudokuBoard = fileSudokuBoardDao.read();
        assertNull(sudokuBoard);
    }

    @Disabled
    @Test
    public void AutoClosableTest() throws Exception {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("tryTest.dat")) {

            SudokuBoard b1 = new SudokuBoard(new BacktrackingSudokuSolver());
            b1.solveGame();
            dao.write(b1);
            var b2 =  dao.read();

            assertEquals(b1,b2);
        }
    }

    @Disabled
    @AfterAll
    public static void clean() {
        File f1 = new File("tryTest.dat");
        File f2 = new File("solvedSudoku.dat");
        f1.delete();
        f2.delete();
    }

}
