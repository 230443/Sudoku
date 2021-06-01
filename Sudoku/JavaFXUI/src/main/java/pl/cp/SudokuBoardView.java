package pl.cp;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import pl.cp.sudoku.model.SudokuBoard;

public class SudokuBoardView {
    private GridPane gridPane;
    private VBox vBox;
    private Button saveButton;

    public Parent asParent() {
        return vBox;
    }

    private SudokuBoardController controller;
    private final SudokuBoard model;

    public SudokuBoardView(SudokuBoardController controller, SudokuBoard model) {
        this.controller = controller;
        this.model = model;

        createAndConfigurePane();
        saveButton = BundleHandler.buttonForKey("button.save");

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(gridPane, saveButton);
    }

    private void createAndConfigurePane() {
        gridPane = new GridPane();


        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        System.out.print(model.toString());
        int[][] tmpBoard = model.getBoard();
        for (int i = 0; i < 9; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(30));
            gridPane.getRowConstraints().add(new RowConstraints(30));
            for (int j = 0; j < 9; j++) {
                if (tmpBoard[i][j] == 0) {
                    TextField textField = new TextField();
                    int tmpi = i;
                    int tmpj = j;
                    textField.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
                        String newText = change.getControlNewText();

                        if (check(newText)) {

                            return null;
                        } else {
                            if (newText.equals("")) {
                                model.set(tmpi, tmpj, 0);
                                textField.setStyle("-fx-control-inner-background: white");
                            } else {
                                if (model.set(tmpi, tmpj, Integer.parseInt(newText))) {
                                    textField.setStyle("-fx-control-inner-background: green");
                                } else {
                                    textField.setStyle("-fx-control-inner-background: red");
                                }
                                System.out.print(tmpi + " " + tmpj + "\n");
                                System.out.print(model.toString());
                            }


                            return change;
                        }

                    }));


                    gridPane.add(textField, j, i);
                } else {
                    gridPane.add(new Label(String.valueOf(tmpBoard[i][j])), j, i);
                }
            }

        }
    }


    private static boolean isNotNumeric(String str) {
        return !(str.matches("[0-9]"));
    }

    private static boolean check(String str) {
        if (str.equals("")) return false;
        return isNotNumeric(str);
    }

}
