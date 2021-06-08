package pl.cp;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;


public class SudokuBoardController {
    private Stage stage;
    private SudokuBoard board;


    @FXML
    Pane pane;

    public void setBoard(Difficulty difficulty) {
        this.board = SudokuBoardPrototype.getInstance(difficulty);
    }


    public SudokuBoardController(SudokuBoard board) {
        this.board = board;
    }
}
