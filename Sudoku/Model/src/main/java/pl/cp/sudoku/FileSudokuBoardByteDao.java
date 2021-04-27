package pl.cp.sudoku;

import pl.cp.sudoku.model.SudokuBoard;

import java.io.*;


public class FileSudokuBoardByteDao implements Dao<SudokuBoard>, AutoCloseable {


    private String filePath;

    public FileSudokuBoardByteDao(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public SudokuBoard read() {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fileIn);) {
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fileOut);) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
    }
}
