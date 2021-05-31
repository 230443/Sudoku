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
//        Parent load = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
//
//        Scene scene = new Scene(load);
//        stage.setScene(scene);
//        stage.titleProperty().bind(BundleHandler.createStringBinding("window.title"));
//        
//
//        stage.show();

        stage.titleProperty().bind(BundleHandler.createStringBinding("window.title"));

        // create content
        BorderPane content = new BorderPane();

        // at the top two buttons
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);

        Button buttonEnglish = BundleHandler.buttonForKey("button.english");
        buttonEnglish.setOnAction((evt) -> switchLanguage(new Locale("en")));
        hbox.getChildren().add(buttonEnglish);

        Button buttonPolish = BundleHandler.buttonForKey("button.polish");
        buttonPolish.setOnAction((evt) -> switchLanguage(new Locale("pl", "PL")));
        hbox.getChildren().add(buttonPolish);

        content.setTop(hbox);

        HBox cbox = new HBox();
        cbox.setPadding(new Insets(5, 5, 5, 5));
        cbox.setSpacing(5);

        Button Easy = BundleHandler.buttonForKey("button.easy");
        Easy.setOnAction((evt) -> switchToSudokuScene(Difficulty.EASY));
        cbox.getChildren().add(Easy);

        Button Normal = BundleHandler.buttonForKey("button.medium");
        Normal.setOnAction((evt) -> switchToSudokuScene(Difficulty.NORMAL));
        cbox.getChildren().add(Normal);


        Button Hard = BundleHandler.buttonForKey("button.hard");
        Hard.setOnAction((evt) -> switchToSudokuScene(Difficulty.HARD));
        cbox.getChildren().add(Hard);

        content.setTop(hbox);

        content.setCenter(cbox);


        stage.setScene(new Scene(content, 400, 200));
        stage.show();

    }

    private void switchLanguage(Locale locale) {

        BundleHandler.setLocale(locale);
    }


    @FXML
    void switchToSudokuScene(Difficulty difficulty) {

        Stage stage=new Stage();

        SudokuBoard model = SudokuBoardPrototype.getInstance(difficulty);

        SudokuBoardController controller = new SudokuBoardController(model);

        SudokuBoardView view = new SudokuBoardView(controller, model);

        stage.setScene(new Scene(view.asParent()));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}