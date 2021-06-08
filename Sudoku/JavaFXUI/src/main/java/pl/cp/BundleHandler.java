package pl.cp;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * I18N utility class..
 */
public final class BundleHandler {

    /**
     * the current selected Locale.
     */
    private static final ObjectProperty<Locale> locale;

    static {
        locale = new SimpleObjectProperty<>(getDefaultLocale());
        locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
    }

    /**
     * get the supported Locales.
     *
     * @return List of Locale objects.
     */
    public static List<Locale> getSupportedLocales() {
        return new ArrayList<>(Arrays.asList(new Locale("en"), new Locale("pl", "PL")));
    }

    /**
     * get the default locale.
     * This is the systems default if contained in the supported locales, english otherwise.
     *
     * @return default locale
     */
    public static Locale getDefaultLocale() {
        Locale sysDefault = Locale.getDefault();
        return getSupportedLocales().contains(sysDefault) ? sysDefault : Locale.ENGLISH;
    }

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        localeProperty().set(locale);
        Locale.setDefault(locale);
    }

    public static ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    /**
     * gets the string with the given key from the resource bundle
     * for the current locale and uses it
     * as first argument to MessageFormat.format,
     * passing in the optional args and returning the result.
     *
     * @param key  message key
     * @param args optional arguments for the message
     * @return localized formatted string
     */
    public static String get(String source, final String key, final Object... args) {

        ResourceBundle bundle = ResourceBundle.getBundle(source, getLocale());
        return MessageFormat.format(bundle.getString(key), args);

    }

    /**
     * gets the string with the given key from the resource bundle
     * for the current locale and uses it
     * as first argument to MessageFormat.format,
     * passing in the optional args and returning the result.
     *
     * @param key  message key
     * @param args optional arguments for the message
     * @return localized formatted string
     */
    public static String get(final String key, final Object... args) {

        ResourceBundle bundle = ResourceBundle.getBundle("difficulty", getLocale());
        return MessageFormat.format(bundle.getString(key), args);

    }


    /**
     * creates a String binding to a localized String for the given message bundle key.
     *
     * @param key key
     * @return String binding
     */
    public static StringBinding createStringBinding(
            String source,
            final String key,
            Object... args) {
        return Bindings.createStringBinding(() -> get(source, key, args), locale);
    }

    public static StringBinding createStringBinding(final String key, Object... args) {
        return Bindings.createStringBinding(() -> get(key, args), locale);
    }


    /**
     * creates a bound Label whose value is computed on language change.
     *
     * @param key the function to compute the value
     * @return Label
     */
    public static Label labelForValue(final String key, final Object... args) {
        Label label = new Label();
        label.textProperty().bind(createStringBinding("pl.cp.MessageBundle", key, args));
        return label;
    }

    /**
     * Creates a bound Button for the given ResourceBundle key.
     *
     * @param key  ResourceBundle key
     * @param args optional arguments for the message
     * @return Button
     */
    public static Button buttonForKey(final String key, final Object... args) {
        Button button = new Button();
        button.textProperty().bind(createStringBinding(key, args));
        return button;
    }


}
