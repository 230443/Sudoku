package pl.cp.sudoku;

public class SudokuBoardDaoFactory {

    public SudokuBoardDaoFactory() {

    }

    public Dao getFileDao(String fileName) {
        return new FileSudokuBoardByteDao(fileName);
    }
}
