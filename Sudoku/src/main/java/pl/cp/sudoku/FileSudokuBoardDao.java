package pl.cp.sudoku;
import  pl.cp.sudoku.model.SudokuBoard;

import java.io.*;
import java.util.Arrays;

public class FileSudokuBoardDao implements Dao<SudokuBoard>,AutoCloseable{


    private String filePath;


    public FileSudokuBoardDao(String filePath){
        this.filePath=filePath;
    }

    @Override
    public SudokuBoard Read() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            SudokuBoard sudokuBoard=new SudokuBoard(new BacktrackingSudokuSolver());
            String line = bufferedReader.readLine();
            int i=0;
            while(line != null) {
                line = bufferedReader.readLine();
                if(line!=null){
                for(int j=0;j<line.length();j++){
                    sudokuBoard.set(i,j,Integer.parseInt(String.valueOf(line.charAt(j))));
                }
                }
            }
            return sudokuBoard;



        } catch (FileNotFoundException e) {

            return new SudokuBoard(new BacktrackingSudokuSolver());
        } catch (IOException e) {

            return new SudokuBoard(new BacktrackingSudokuSolver());
        }



    }

    @Override
    public void Write(SudokuBoard obj) {
        try(FileWriter fileWriter = new FileWriter(this.filePath)) {
            fileWriter.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing?");
    }
}
