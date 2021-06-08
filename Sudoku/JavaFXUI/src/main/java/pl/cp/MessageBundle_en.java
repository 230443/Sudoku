package pl.cp;

import java.util.ListResourceBundle;

public class MessageBundle_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"author", "Adam Czyzewski and Daniel Baraniak"},
                {"lang", "in english"}
        };
    }
}
