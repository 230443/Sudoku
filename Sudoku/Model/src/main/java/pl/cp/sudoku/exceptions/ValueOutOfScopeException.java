package pl.cp.sudoku.exceptions;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ValueOutOfScopeException.
 */

public class ValueOutOfScopeException extends ApplicationException {
    private static final ResourceBundle messages;
    //Message keys

    public static final String ValueOutOfScope = "message.outOfBound";

    static {
        Locale locale = Locale.getDefault(Locale.Category.DISPLAY);
        messages = ResourceBundle.getBundle("exceptions.messages", locale);
    }

    public ValueOutOfScopeException(String message) {
        super(message);
    }

    public ValueOutOfScopeException(String message, Throwable cause) {
        super(message, cause);
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
