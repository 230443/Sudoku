package pl.cp.sudoku.model.sudokufield;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ValueOutOfScopeException.
 */

public class UnmodifiableSudokuFieldException extends RuntimeException {
    private static final ResourceBundle messages;

    public static final String UNMODIFIABLE_VALUE = "message.unmodifiableSudokuField";

    static {
        Locale locale = Locale.getDefault(Locale.Category.DISPLAY);
        messages = ResourceBundle.getBundle("exceptions.messages", locale);
    }

    UnmodifiableSudokuFieldException(String msg) {
        super(msg);
    }

    @Override
    public String getLocalizedMessage() {
        String message;
        try {
            //Exception message is a key
            message = messages.getString(getMessage());
        } catch (MissingResourceException mre) {
            message = "No resource for " + getMessage() + "key";
        }
        return message;
    }
}
