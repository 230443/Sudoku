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
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.SudokuSolver;
import pl.cp.sudoku.model.SudokuBoard;

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

            Stage stage=new Stage();

            SudokuBoard model = SudokuBoardPrototype.getInstance(Difficulty.HARD);

            SudokuBoardController controller = new SudokuBoardController(model);

            SudokuBoardView view = new SudokuBoardView(controller, model);

            stage.setScene(new Scene(view.asParent()));
            stage.show();

    }

}
