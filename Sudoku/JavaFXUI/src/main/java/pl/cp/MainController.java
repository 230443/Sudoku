package pl.cp;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.cp.sudoku.Difficulty;

import java.io.IOException;


public class MainController {

    private Difficulty difficulty;
    @FXML
    private Button EASY;

    @FXML
    private Button NORMAL;

    @FXML
    private Button HARD;


    public void handleEasy(ActionEvent event){
        difficulty=Difficulty.EASY;
        switchToSudokuScene(event);
    }

    public void handleNormal(ActionEvent event){
        difficulty=Difficulty.NORMAL;
        switchToSudokuScene(event);
    }

    public void handleHard(ActionEvent event){
        difficulty=Difficulty.HARD;
        switchToSudokuScene(event);
    }


    @FXML
    void switchToSudokuScene(ActionEvent event) {

        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/SudokuBoardScene.fxml"));
            Parent root=(Parent)loader.load();
            SudokuBoardController sudokuBoardController=loader.getController();
           sudokuBoardController.setBoard(this.difficulty);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
