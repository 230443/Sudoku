package pl.cp;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import pl.cp.sudoku.Dao;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardDaoFactory;
import pl.cp.sudoku.model.SudokuBoard;

import java.util.function.UnaryOperator;

public class SudokuBoardView {
    private final SudokuBoard model;
    private GridPane gridPane;
    private VBox vBox;
    private Button saveButton;
    Label boardLabel = new Label();
    private SudokuBoardController controller;
    SudokuBoardStringProperty boardStringProperty;


    public SudokuBoardView(SudokuBoardController controller, SudokuBoard model) {
        this.controller = controller;
        this.model = model;

        boardStringProperty = new SudokuBoardStringProperty(model);
        boardLabel.textProperty().bindBidirectional(boardStringProperty);

        createAndConfigurePane();

        saveButton = BundleHandler.buttonForKey("button.save");
        saveButton.setOnAction((evt) -> saveBoard());


        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(gridPane, boardLabel, saveButton);


    }

    private static boolean isNotNumeric(String str) {
        return !(str.matches("[0-9]"));
    }

    private static boolean check(String str) {
        if (str.equals("")) return false;
        return isNotNumeric(str);
    }

    private static int getNumberFromString(String str) {
        if (str.equals("")) return 0;
        return Integer.parseInt(str);
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
        System.out.print(model.toString());

        for (int y = 0; y < 9; y++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(30));
            gridPane.getRowConstraints().add(new RowConstraints(30));

            for (int x = 0; x < 9; x++) {
                if (!model.isFieldUnmodifiable(x, y)) {
                    TextField textField = new TextField();
                    if (model.get(x, y) != 0) {
                        textField.setText(String.valueOf(model.get(x, y)));
                    }

                    textField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, integerFilter));
                    SudokuFieldProperty sudokuFieldProperty = new SudokuFieldProperty(model, x, y);
                    Bindings.bindBidirectional(textField.textProperty(), sudokuFieldProperty, converter);
                    textField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                selectedStudent.setFirstName(newValue);
                            System.out.println("Changed");
                            sudokuFieldProperty.set((Integer) converter.fromString(newValue));
                            boardStringProperty.fireValueChangedEvent();

                        }
                    });

                    Bindings.bindBidirectional(textField.textProperty(), boardStringProperty);


                    gridPane.add(textField, x, y);
                } else {
                    gridPane.add(new Label(String.valueOf(model.get(x,y))), x, y);
                }
            }

        }
    }

    private final StringConverter converter = new SudokuStringConverter();

    private void saveBoard() {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("savedBoard.dat")) {

            dao.write(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
