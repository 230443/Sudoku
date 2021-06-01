package pl.cp.sudoku;

import pl.cp.sudoku.BacktrackingSudokuSolver;
import pl.cp.sudoku.model.SudokuBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoardPrototype {

    private static class Point {
        int x;
        int y;

        private static List<Integer> possibleIndex = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));

        static Point getPoint() {
            Point result = new Point();

            Collections.shuffle(possibleIndex);
            result.x = possibleIndex.get(0);
            Collections.shuffle(possibleIndex);
            result.y = possibleIndex.get(0);

            return result;
        }
    }



    public static SudokuBoard getInstance() {
        return new SudokuBoard(new BacktrackingSudokuSolver());
    }

    public static SudokuBoard getInstance(Difficulty level) {
        SudokuBoard board = getInstance();
        board.solveGame();

        for (int i = 0; i < level.numberOfPairs; i++) {
            Point p;

            do {
                p = Point.getPoint();
            }
            while (board.get(p.x, p.y) == 0);

            board.set(p.x, p.y, 0);
            board.set(8 - p.x, 8 - p.y, 0);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i,j) != 0) {
                    board.makeFieldUnmodifiable(i,j);
                }
            }
        }

        return board;
    }
}
