package pl.cp.sudoku.model.sudokufield;

/**
 * ValueOutOfScopeException.
 */

public class ValueOutOfScopeException extends RuntimeException {
    ValueOutOfScopeException(String msg) {
        super(msg);
    }
}
