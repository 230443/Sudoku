package pl.cp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SudokuBoardController  {
    private Stage stage;
    private SudokuBoard board;



    @FXML
    Pane PANE;



    public void setBoard(Difficulty difficulty){

        board = SudokuBoardPrototype.getInstance(difficulty);
    }



   @FXML
    public void initialize() {
        for(int i=0;i< 9*50;i+=50)
        {
            for (int j=0;j<9*50;j+=50){

            }
        }
    }

}
