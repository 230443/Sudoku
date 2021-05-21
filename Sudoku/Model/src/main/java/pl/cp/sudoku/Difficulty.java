package pl.cp.sudoku;

public enum Difficulty {
    EASY(5),
    NORMAL(10),
    HARD(15);

    int numberOfPairs;

    Difficulty(int n) {
        numberOfPairs = n;
    }
}
