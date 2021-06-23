package pl.cp;


import java.util.Locale;
import java.util.Optional;
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
import pl.cp.sudoku.dao.DaoException;
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
        load.setOnAction((evt) -> showLoadBoardDialog());
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

        SudokuBoardView view = new SudokuBoardView(model);

        stage.setScene(new Scene(view.asParent()));
        stage.show();

    }

    @FXML
    void load(String name) {

        Stage stage = new Stage();

        SudokuBoard model = null;
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getDao(name)) {
            model = dao.read();
        } catch (DaoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setHeaderText(e.getLocalizedMessage());
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (model == null) {
            return;
        }

        SudokuBoardView view = new SudokuBoardView(model);

        stage.setScene(new Scene(view.asParent()));
        stage.show();

    }

    private void showLoadBoardDialog() {
        try {
            ChoiceDialog<String> dialog = new ChoiceDialog<>("", DbConnector.getSudokuBoardNames());
            dialog.setTitle(BundleHandler.get("dialog.selectionTitle"));
            dialog.setHeaderText(BundleHandler.get("dialog.selectionHeader"));
            dialog.setContentText(BundleHandler.get("dialog.selectionContent"));
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(this::load);
        } catch (DaoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
        }

    }


}