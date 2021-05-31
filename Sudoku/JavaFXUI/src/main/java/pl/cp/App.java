package pl.cp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;

import java.io.IOException;
import java.util.Locale;


/**
 * JavaFX App
 */
public class App extends Application {

    private Difficulty difficulty;

    @Override
    public void start(Stage stage) throws IOException {

        MainView view = new MainView();

        view.initialize(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}