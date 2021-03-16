package pl.cp.sudoku;

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
        if (isSafe(x, y, value)) {
            this.board[x][y] = value;
            return true;
        }
        if (value == 0) {
            this.board[x][y] = value;
            return true;
        }
        return false;
    }

    private int[][] board = new int[9][9];
    private final SudokuSolver solver;

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
