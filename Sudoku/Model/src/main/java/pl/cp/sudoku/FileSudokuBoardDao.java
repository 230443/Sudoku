package pl.cp.sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.cp.sudoku.model.SudokuBoard;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {


    private final String filePath;

    public FileSudokuBoardDao(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public SudokuBoard read() {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fileIn)) {
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fileOut)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() {
    }
}
