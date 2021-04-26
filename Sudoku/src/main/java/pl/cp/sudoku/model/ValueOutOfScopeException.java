package pl.cp.sudoku.model;

/**
 * ValueOutOfScopeException.
 */

public class ValueOutOfScopeException extends RuntimeException {
    ValueOutOfScopeException(String msg) {
        super(msg);
    }
}
