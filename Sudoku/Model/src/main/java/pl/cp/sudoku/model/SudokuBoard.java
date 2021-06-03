package pl.cp.sudoku.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.SudokuSolver;
import pl.cp.sudoku.model.sudokuboardelement.FieldAlreadyExistException;
import pl.cp.sudoku.model.sudokuboardelement.SudokuBox;
import pl.cp.sudoku.model.sudokuboardelement.SudokuColumn;
import pl.cp.sudoku.model.sudokuboardelement.SudokuRow;
import pl.cp.sudoku.model.sudokufield.SudokuField;
import pl.cp.sudoku.model.sudokufield.UnmodifiableSudokuField;
import pl.cp.sudoku.model.sudokufield.ValueOutOfScopeException;


/**
 * Sudoku Board.
 */

public class SudokuBoard implements Serializable, Cloneable {

    public SudokuBoard(SudokuSolver solver) {
        initializeBoard();
        this.solver = solver;
    }

    public void solveGame() {
        solver.solve(this);
    }


    /**
     * gets Board in format of int[][].
     * @return copy of board
     */
    public int[][] getBoard() {
        int[][] copy = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = board[i][j].getValue();
            }
        }

        return copy;
    }

    /**
     * Gets the integer value in given position.
     * @param x x coordinate
     * @param y y coordinate
     * @return Value of sudokuField.
     */
    public int get(int x, int y) {
        return board[y][x].getValue();
    }

    /**
     * sets the value into the field to which coordinates were provided.
     * @param x x coordinate
     * @param y y coordinate
     * @param value value to be set.
     * @return true if value was set correctly, false otherwise.
     */
    public boolean set(int x, int y, int value) {
        try {
            board[y][x].setValue(value);
        } catch (FieldAlreadyExistException | ValueOutOfScopeException e) {
            return false;
        }
        return true;
    }

    private final SudokuSolver solver;

    /**
     * Gets copy of SudokuRow at given position.
     * @param y y coordinate
     * @return SudokuRow
     */
    public SudokuRow getRow(int y) {
        return new SudokuRow(Arrays.asList(board[y]));
    }

    /**
     * Gets copy of SudokuColumn at given position.
     * @param x x coordinate
     * @return SudokuColumn
     */
    public SudokuColumn getColumn(int x) {
        ArrayList<SudokuField> column = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            column.add(board[i][x]);
        }

        return new SudokuColumn(column);
    }

    /**
     * Gets copy of sudokuBox at given position.
     * @param x x coordinate
     * @param y y coordinate
     * @return SudokuBox
     */
    public SudokuBox getBox(int x, int y) {
        SudokuField[] box = new SudokuField[9];
        x *= 3;
        y *= 3;
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.board[y + i], x, box, i * 3, 3);
        }
        return new SudokuBox(Arrays.asList(box));
    }

    private SudokuField[][] board = new SudokuField[9][9];

    private void initializeBoard() {   //initialization block

        SudokuField[][] tmpBoard = new SudokuField[9][9];
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
                tmpBoard[y][x] = new SudokuField();

                columns[x].addField(tmpBoard[y][x]);
                rows[y].addField(tmpBoard[y][x]);
                boxes[(y / 3) * 3 + (x / 3)].addField(tmpBoard[y][x]);
            }
        }
        board = tmpBoard;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                tmp.append(get(y, x)).append("  ");
                if (x == 2 || x == 5) {
                    tmp.append("|  ");
                }
            }
            tmp.append("\n");
            if (y == 2 || y == 5) {
                tmp.append("---------+-----------+---------\n");
            }
        }
        return tmp.toString();
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

    @Override
    public SudokuBoard clone() {

        SudokuBoard result = SudokuBoardPrototype.getInstance();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                result.board[column][row].setValue(board[column][row].getValue());
                if (isFieldUnmodifiable(row,column)) {
                    result.makeFieldUnmodifiable(row, column);
                }
            }
        }

        return result;
    }

    public boolean checkBoard() {
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

    public void makeFieldUnmodifiable(int x, int y) {
        board[y][x] = new UnmodifiableSudokuField(board[y][x]);
    }

    public boolean isFieldUnmodifiable(int x, int y) {
        return board[y][x] instanceof UnmodifiableSudokuField;
    }

}