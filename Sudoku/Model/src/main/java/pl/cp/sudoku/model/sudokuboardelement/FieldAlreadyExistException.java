package pl.cp.sudoku.model.sudokuboardelement;

/**
 * FieldAlreadyExistException.
 */


public class FieldAlreadyExistException extends RuntimeException {
    FieldAlreadyExistException(String msg) {
        super(msg);
    }
}
