package pl.cp.sudoku.dao;

import pl.cp.sudoku.model.SudokuBoard;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {
    }

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> getJdbcDao(String boardName) {
        return new JdbcSudokuBoardDao(boardName);
    }
}
