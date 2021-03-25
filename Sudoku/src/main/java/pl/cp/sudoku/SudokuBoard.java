package pl.cp.sudoku;

import pl.cp.sudoku.elements.SudokuBox;
import pl.cp.sudoku.elements.SudokuColumn;
import pl.cp.sudoku.elements.SudokuField;
import pl.cp.sudoku.elements.SudokuRow;

/**
 * Sudoku Board.
 */

public class SudokuBoard {

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        initializeSudokuFields();
    }

    public void solveGame() {
        solver.solve(this);
    }

    public int[][] getBoard() {

        int[][] copy = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = sudokuFields[i][j].getValue();
            }
        }

        return copy;
    }

    public int get(int x, int y) {
        return sudokuFields[x][y].getValue();
    }

    public boolean set(int x, int y, int value) {

        int v = sudokuFields[x][y].getValue();
        sudokuFields[x][y].setValue(value);

        if (!checkBoard()) {
            sudokuFields[x][y].setValue(v);
            return false;
        }
        return true;
    }

    private final SudokuSolver solver;

    public SudokuRow getRow(int y) {
        return new SudokuRow(sudokuFields[y]);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] column = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            column[i] = sudokuFields[i][x];
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] box = new SudokuField[9];
        x *= 3;
        y *= 3;
        for (int i = 0; i < 3; i++) {
            System.arraycopy(this.sudokuFields[y + i], x, box, i * 3, 3);
        }

        return new SudokuBox(box);
    }

    private SudokuField[][] sudokuFields = new SudokuField[9][9];

    private void initializeSudokuFields() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sudokuFields[y][x] = new SudokuField();
            }
        }

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