package pl.cp.sudoku.dao;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DaoException extends RuntimeException {
    private static final ResourceBundle messages;

    public static final String BOARD_CORRUPTED = "message.boardCorrupted";
    public static final String BOARD_NOT_FOUND = "message.boardNotFound";
    public static final String CONNECTION_ERROR = "message.connectionError";

    static {
        Locale locale = Locale.getDefault(Locale.Category.DISPLAY);
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public DaoException(String msg) {
        super(msg);
    }

    DaoException(String message, Throwable cause) {
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
