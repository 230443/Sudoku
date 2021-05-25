package pl.cp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
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
                view.add(new TextField(String.valueOf(tmpBoard[i][j])), i, j);
            }

        }
    }

}
