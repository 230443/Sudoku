package pl.cp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

import static java.lang.String.valueOf;


public class SudokuBoardController  {
    private Stage stage;
    private SudokuBoard board;



    @FXML
    Pane PANE;



    public void setBoard(Difficulty difficulty){

       this.board = SudokuBoardPrototype.getInstance(difficulty);

    }



   @FXML
    public void initialize() {
        for(int i=0;i< 9;i++)
        {
            for (int j=0;j<9;j++){
                TextField t=new TextField(valueOf(board.get(i,j)));
                t.setPrefHeight(50);
                t.setPrefHeight(50);

            }
        }
    }

}
