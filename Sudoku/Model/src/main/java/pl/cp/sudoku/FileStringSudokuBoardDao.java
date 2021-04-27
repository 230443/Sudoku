package pl.cp.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import pl.cp.sudoku.model.SudokuBoard;


public class FileStringSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {


    private final String filePath;


    public FileStringSudokuBoardDao(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public SudokuBoard read() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
            String line = bufferedReader.readLine();
            int i = 0;
            while (line != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    for (int j = 0; j < line.length(); j++) {
                        sudokuBoard.set(i, j, Integer.parseInt(String.valueOf(line.charAt(j))));
                    }
                }
            }
            return sudokuBoard;
        } catch (IOException e) {
            return new SudokuBoard(new BacktrackingSudokuSolver());
        }


    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileWriter fileWriter = new FileWriter(this.filePath)) {
            fileWriter.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
    }
}
