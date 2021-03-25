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
        for (int i = 0; i < board.length; i++) {
            int[] singleRow = board[i];
            copy[i] = new int[9];
            System.arraycopy(singleRow, 0, copy[i], 0, 9);
        }
        return copy;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public boolean set(int x, int y, int value) {
        boolean isSafe = (isSafe(x, y, value) || value == 0);
        int v = sudokuFields[x][y].getValue();
        sudokuFields[x][y].setValue(value);
        boolean isVerified = checkBoard();


        boolean isSame = isSafe == isVerified;
        if (!isSame) {
            System.out.println("not the same");
        }

        if (isSafe(x, y, value) || value == 0) {
            this.board[x][y] = value;
            sudokuFields[x][y].setValue(value);
            return true;
        }
        sudokuFields[x][y].setValue(v);
        return false;
    }

    private int[][] board = new int[9][9];
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

    private boolean isSafe(int row, int col, int guess) {
        return isRowSafe(row, guess) && isColSafe(col, guess) && isBoxSafe(row, col, guess);
    }

    private boolean isRowSafe(int row, int guess) {
        for (int value :
                board[row]) {
            if (value == guess) {
                return false;
            }
        }
        return true;
    }

    private boolean isColSafe(int col, int guess) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == guess) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoxSafe(int row, int col, int guess) {
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + j] == guess) {
                    return false;
                }
            }
        }
        return true;
    }

}