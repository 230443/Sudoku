package pl.cp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;

import java.io.IOException;
import java.util.Locale;

public class MainView{

    private Difficulty difficulty;
    private BorderPane content = new BorderPane();

    public Parent asParent() {
        return content;
    }

    public void initialize(Stage stage) {



        stage.titleProperty().bind(BundleHandler.createStringBinding("window.title"));


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
        cbox.setPadding(new Insets(15, 5, 5, 5));
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

        Button Load = BundleHandler.buttonForKey("button.load");
        Hard.setOnAction((evt) -> switchToSudokuScene(Difficulty.HARD));
        cbox.getChildren().add(Load);


        HBox dbox=new HBox();
        Label label=BundleHandler.labelForValue("author");
        dbox.getChildren().add(label);


        content.setTop(hbox);
        content.setCenter(cbox);
        content.setBottom(dbox);


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


}