package pl.cp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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

        int[][] tmpBoard = model.getBoard();
        for (int i = 0; i < 9; i++) {
            view.getColumnConstraints().add(new ColumnConstraints(30));
            view.getRowConstraints().add(new RowConstraints(30));
            for (int j = 0; j < 9 ; j++) {
                if (tmpBoard[i][j] == 0) {
                    TextField textField=new TextField();
                    textField.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
                        String newText = change.getControlNewText();

                            if (check(newText)) {
                                return null;
                            } else  {
                                return change;
                            }

                    }));

                    view.add(textField, i, j);
                } else {
                    view.add(new Label(String.valueOf(tmpBoard[i][j])), i, j);
                }
            }

        }
    }

    private static boolean isNotNumeric(String str){
        return !(str.matches("[0-9]"));
    }

    private static boolean check(String str){
        if (str.equals("")) return false;
         return isNotNumeric(str);
    }

}
