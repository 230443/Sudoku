package pl.cp;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import pl.cp.sudoku.Difficulty;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;

public class SudokuBoardView {
    private GridPane view;

    public Parent asParent() {
        return view;
    }

    private SudokuBoardController controller;
    private final SudokuBoard model;

    public SudokuBoardView(SudokuBoardController controller, SudokuBoard model) {
        this.controller = controller;
        this.model = model;

        createAndConfigurePane();
    }

    private void createAndConfigurePane() {
        view = new GridPane();


        view.setAlignment(Pos.CENTER);
        view.setHgap(5);
        view.setVgap(5);
        System.out.print(model.toString());
        int[][] tmpBoard = model.getBoard();
        for (int i = 0; i < 9; i++) {
            view.getColumnConstraints().add(new ColumnConstraints(30));
            view.getRowConstraints().add(new RowConstraints(30));
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


                    view.add(textField, j, i);
                } else {
                    view.add(new Label(String.valueOf(tmpBoard[i][j])), j, i);
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
