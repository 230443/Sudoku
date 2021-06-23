package pl.cp;

import java.util.Optional;
import java.util.function.UnaryOperator;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import pl.cp.sudoku.dao.Dao;
import pl.cp.sudoku.dao.DaoException;
import pl.cp.sudoku.dao.DbConnector;
import pl.cp.sudoku.dao.SudokuBoardDaoFactory;
import pl.cp.sudoku.model.SudokuBoard;

public class SudokuBoardView {
    private final SudokuBoard model;
    private GridPane gridPane;
    private VBox vBox;
    private Button saveButton;
    Label boardLabel = new Label();
    //SudokuBoardStringProperty boardStringProperty;

    /**
     * View for presenting and solving sudoku.
     * @param model SudokuBoard
     */
    public SudokuBoardView(SudokuBoard model) {

        this.model = model;
        //model.isCheckingOn = false;

        //boardStringProperty = new SudokuBoardStringProperty(model);
        //boardLabel.textProperty().bindBidirectional(boardStringProperty);

        createAndConfigurePane();

        saveButton = BundleHandler.buttonForKey("button.save");
        saveButton.setOnAction((evt) -> showAddBoardDialog());



        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(gridPane, boardLabel, saveButton);


    }


    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[0-9]?")) {
            return change;
        }
        return null;
    };


    public Parent asParent() {
        return vBox;
    }

    private void createAndConfigurePane() {
        gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int y = 0; y < 9; y++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(30));
            gridPane.getRowConstraints().add(new RowConstraints(30));

            for (int x = 0; x < 9; x++) {
                if (!model.isFieldUnmodifiable(x, y)) {
                    TextField textField = new TextField();
                    if (model.get(x, y) != 0) {
                        textField.setText(String.valueOf(model.get(x, y)));
                    }

                    textField.setTextFormatter(
                            new TextFormatter<>(new IntegerStringConverter(), null, integerFilter));
                    SudokuFieldProperty sudokuFieldProperty = new SudokuFieldProperty(model, x, y);
                    Bindings.bindBidirectional(
                            textField.textProperty(), sudokuFieldProperty, converter);

                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        System.out.println("Changed");
                        sudokuFieldProperty.set((Integer) converter.fromString(newValue));
                        //boardStringProperty.fireValueChangedEvent();

                        if (model.isCheckingOn) {
                            if (converter.fromString(newValue).equals(0)) {
                                textField.setStyle("-fx-control-inner-background: white");
                            } else if (sudokuFieldProperty.setValue(
                                    (Integer) converter.fromString(newValue))) {
                                textField.setStyle("-fx-control-inner-background: green");
                            } else {
                                textField.setStyle("-fx-control-inner-background: red");
                            }
                        }
                    });

                    //Bindings.bindBidirectional(textField.textProperty(), boardStringProperty);


                    gridPane.add(textField, x, y);
                } else {
                    gridPane.add(new Label(String.valueOf(model.get(x, y))), x, y);
                }
            }

        }
    }

    private final StringConverter converter = new SudokuStringConverter();


    private void showAddBoardDialog() {
        TextInputDialog dialog = new TextInputDialog("New board");
        dialog.setTitle("Save game");
        dialog.setHeaderText("Choose board name");
        dialog.setContentText("Board name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(this::saveBoard);
    }

    private void showSelectBoardDialog() {
        try {
            ChoiceDialog<String> dialog = new ChoiceDialog<>("", DbConnector.getSudokuBoardNames());
            dialog.setTitle("Board selection");
            dialog.setHeaderText("Select board ");
            dialog.setContentText("Choose your sudoku board:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(this::saveBoard);
        } catch (DaoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
        }
    }

    private void showChooseMethodDialog() {

            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Board selection");
            dialog.setHeaderText("Select board ");
            dialog.setContentText("Choose your sudoku board:");

            ButtonType select = new ButtonType("button.select");
            ButtonType add = new ButtonType("button.add");

            dialog.getButtonTypes().setAll(select, add);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == select) {
                showSelectBoardDialog();
            } else if (result.get() == add) {
                showAddBoardDialog();
            } else {
                dialog.close();
            }
    }

    private void saveBoard(String boardName) {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getDao(boardName)) {

            dao.write(model);

        } catch (DaoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setHeaderText(e.getLocalizedMessage());
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
