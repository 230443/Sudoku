package pl.cp.sudoku.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;


import static org.junit.jupiter.api.Assertions.*;


public class JdbcSudokuBoardDaoTest {


    private SudokuBoard writeSolvedBoard(String name) {
        SudokuBoard sudokuBoard = SudokuBoardPrototype.getInstance(Difficulty.HARD);
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao(name)) {
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


    @Test
    public void WriteAndReadBoardTest() {

        SudokuBoard b1 = writeSolvedBoard("Test hard board");
        SudokuBoard b2 = null;
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("Test hard board")) {
            b2 = dao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(b1, b2);
    }

    @Test
    public void readNonExistingBoardTest() {
        SudokuBoard board = SudokuBoardPrototype.getInstance();
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("non existing board")) {
            board = dao.read();
            fail();
        } catch (DaoException e) {
        } catch (Exception e) {
            fail();
        }
        //assertNull(board);
    }


    @Test
    public void readBadBoardTest() throws SQLException {
        writeSolvedBoard("bad board");
        Connection c = DbConnector.connect();
        Statement statement = c.createStatement();
        statement.execute("DELETE FROM `field_values` WHERE `y`='1' AND `board_id` LIKE (SELECT id FROM `sudokuboards` WHERE boardname='bad board')");

        c.close();

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("bad board")) {
            dao.read();
            fail();
        } catch (DaoException e) {
        } catch (Exception e) {
            fail();
        }


    }

    @AfterAll
    public static void clean() throws SQLException {
        Connection c = DbConnector.connect();
        Statement statement = c.createStatement();
        statement.execute("DELETE FROM `field_values` WHERE `board_id` LIKE (SELECT id FROM `sudokuboards` WHERE boardname='bad board')");
        statement.execute("DELETE FROM `field_values` WHERE `board_id` LIKE (SELECT id FROM `sudokuboards` WHERE boardname='Test hard board')");
        statement.execute("DELETE FROM `sudokuboards` WHERE boardname='bad board'");
        statement.execute("DELETE FROM `sudokuboards` WHERE  boardname='Test hard board'");
        c.close();
    }

}
