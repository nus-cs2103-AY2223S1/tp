package seedu.workbook.ui;

import javafx.scene.control.Label;

/**
 * Custom wrapper on a JavaFX Label to allow custom css styling.
 */
public class LanguageTagLabel extends Label {
    /**
     * Creates a {@code LanguageTabLabel} with the input text.
     */
    public LanguageTagLabel(String text) {
        super(text);
        getStyleClass().setAll("languageTagLabel");
    }

}
