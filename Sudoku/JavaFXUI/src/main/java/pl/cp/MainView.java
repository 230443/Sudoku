package pl.cp;


import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.dao.Dao;
import pl.cp.sudoku.dao.DbConnector;
import pl.cp.sudoku.dao.SudokuBoardDaoFactory;
import pl.cp.sudoku.model.SudokuBoard;



public class MainView {

    private Difficulty difficulty;
    private BorderPane content = new BorderPane();

    public Parent asParent() {
        return content;
    }

    /**
     * Initializes the stage.
     * @param stage Stage where it will be placed
     */
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

        Button easy = BundleHandler.buttonForKey("button.easy");
        easy.setOnAction((evt) -> switchToSudokuScene(Difficulty.EASY));
        cbox.getChildren().add(easy);

        Button normal = BundleHandler.buttonForKey("button.medium");
        normal.setOnAction((evt) -> switchToSudokuScene(Difficulty.NORMAL));
        cbox.getChildren().add(normal);

        Button hard = BundleHandler.buttonForKey("button.hard");
        hard.setOnAction((evt) -> switchToSudokuScene(Difficulty.HARD));
        cbox.getChildren().add(hard);


        Button load = BundleHandler.buttonForKey("button.load");
        load.setOnAction((evt) -> load());
        cbox.getChildren().add(load);


        HBox dbox = new HBox();
        Label label = BundleHandler.labelForValue("author");
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

        Stage stage = new Stage();

        SudokuBoard model = SudokuBoardPrototype.getInstance(difficulty);

        SudokuBoardController controller = new SudokuBoardController(model);

        SudokuBoardView view = new SudokuBoardView(controller, model);

        stage.setScene(new Scene(view.asParent()));
        stage.show();

    }

    @FXML
    void load() {

        Stage stage = new Stage();

        SudokuBoard model;
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getDao("savedBoard.dat")) {
            model = dao.read();
        } catch (Exception e) {
            throw new RuntimeException("Cannot load the board from file");
        }

        if (model == null) {
            return;
        }
        SudokuBoardController controller = new SudokuBoardController(model);

        SudokuBoardView view = new SudokuBoardView(controller, model);

        stage.setScene(new Scene(view.asParent()));
        stage.show();

    }


}