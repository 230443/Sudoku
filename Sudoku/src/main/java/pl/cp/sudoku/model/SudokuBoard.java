package pl.cp.sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.cp.sudoku.SudokuSolver;
import pl.cp.sudoku.model.sudokuboardelement.FieldAlreadyExistException;
import pl.cp.sudoku.model.sudokuboardelement.SudokuBox;
import pl.cp.sudoku.model.sudokuboardelement.SudokuColumn;
import pl.cp.sudoku.model.sudokuboardelement.SudokuRow;


/**
 * Sudoku Board.
 */

public class SudokuBoard {

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
    }

    public void solveGame() {
        solver.solve(this);
    }

    public int[][] getBoard() {

        int[][] copy = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = board[i][j].getValue();
            }
        }

        return copy;
    }

    public int get(int x, int y) {
        return board[x][y].getValue();
    }

    public boolean set(int x, int y, int value) {
        try {
            board[x][y].setValue(value);
        } catch (FieldAlreadyExistException e) {
            return false;
        } catch (ValueOutOfScopeException e) {
            return false;
        }
        return true;
    }

    private final SudokuSolver solver;

    public SudokuRow getRow(int y) {
        return new SudokuRow(Arrays.asList(board[y]));
    }

    public SudokuColumn getColumn(int x) {
        ArrayList<SudokuField> column = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            column.add(board[i][x]);
        }

        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] box = new SudokuField[9];
        x *= 3;
        y *= 3;
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.board[y + i], x, box, i * 3, 3);
        }
        return new SudokuBox(Arrays.asList(box));
    }

    private final SudokuField[][] board = new SudokuField[9][9];

    {   //initialization block

        //initialize rows and columns
        SudokuRow[] rows = new SudokuRow[9];
        SudokuColumn[] columns = new SudokuColumn[9];
        SudokuBox[] boxes = new SudokuBox[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new SudokuRow();
            columns[i] = new SudokuColumn();
            boxes[i] = new SudokuBox();
        }

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                board[y][x] = new SudokuField();

                columns[x].addField(board[y][x]);
                rows[y].addField(board[y][x]);
                boxes[(y / 3) * 3 + (x / 3)].addField(board[y][x]);
            }
        }
    }

    @Override
    public String toString() {
        String tmp="";
        for(int i=0;i<board.length;i++){
            for(int j=0; j<board.length;j++){
                tmp+=String.valueOf(get(i,j));
            }
            tmp+="\n";
        }
        return tmp;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(board).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder().append(board, that.board).isEquals();
    }

    private boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getColumn(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }

        return true;
    }

}