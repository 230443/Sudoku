package pl.cp.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.cp.sudoku.model.*;
import pl.cp.sudoku.model.sudokuboardelement.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class FileSudokuBoardDaoTest {


    @Test
    public void writeEmptyBoardTest(){
        SudokuBoard sudokuBoard=new SudokuBoard(new BacktrackingSudokuSolver());
        FileSudokuBoardDao fileSudokuBoardDao= new FileSudokuBoardDao("test.txt");
        fileSudokuBoardDao.write(sudokuBoard);
    }

    @Test
    public void readEmptyBoardTest(){

        FileSudokuBoardDao fileSudokuBoardDao= new FileSudokuBoardDao("test.txt");
        SudokuBoard sudokuBoard=fileSudokuBoardDao.read();
        System.out.print(sudokuBoard.toString());
    }
}
