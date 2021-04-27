package pl.cp.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.model.*;
import pl.cp.sudoku.model.sudokuboardelement.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class FileSudokuBoardDaoTest {

    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @Test
    public void writeEmptyBoardTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        Dao<SudokuBoard> fileSudokuBoardDao = factory.getFileDao("test.txt");
        fileSudokuBoardDao.write(sudokuBoard);
    }

    @Test
    public void writeToWrongFileTest(){
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        Dao fileSudokuBoardDao = factory.getFileDao("&?/|.txt");
        fileSudokuBoardDao.write(sudokuBoard);
    }

    @Test
    public void readEmptyBoardTest(){

        Dao<SudokuBoard> fileSudokuBoardDao = factory.getFileDao("test.txt");
        SudokuBoard sudokuBoard=fileSudokuBoardDao.read();
        System.out.print(sudokuBoard.toString());
    }

    @Test
    public void readNonExistingFileTest() {
        Dao fileSudokuBoardDao = factory.getFileDao("notFound");
        fileSudokuBoardDao.read();
    }

    @Test
    public void readBadFileTest() {
        Dao fileSudokuBoardDao = factory.getFileDao("faulty.txt");
        SudokuBoard sudokuBoard = (SudokuBoard) fileSudokuBoardDao.read();
        assertNotEquals(null, sudokuBoard);
    }

    @Test
    public void AutoClosableTest() {
        try (FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao("test.txt")) {
            SudokuBoard sudokuBoard = fileSudokuBoardDao.read();
            SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
            assertEquals(sudokuBoard1, sudokuBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
