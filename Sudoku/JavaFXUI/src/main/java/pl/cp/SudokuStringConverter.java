package pl.cp;

import javafx.util.StringConverter;

public class SudokuStringConverter  extends StringConverter<Integer> {
    public SudokuStringConverter() {
    }

    /**
     * Converts String to Integer.
     * @param var1 String
     * @return  Integer value, 0 if null
     */
    public Integer fromString(String var1) {
        if (var1 == null) {
            return null;
        } else {
            var1 = var1.trim();
            return var1.length() < 1 ? 0 : Integer.valueOf(var1);
        }
    }

    public String toString(Integer var1) {
        return var1 == null || var1 == 0 ? "" : Integer.toString(var1);
    }
}
