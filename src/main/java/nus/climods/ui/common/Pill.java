package nus.climods.ui.common;

import javafx.scene.control.Button;

/**
 * A button UI component in a shape of a pill.
 */
public class Pill extends Button {

    private static final String DEFAULT_STYLE = "-fx-border-width:0;-fx-background-radius: 7px;-fx-padding: 3;"
        + "-fx-font-size: 10;-fx-font-weight: bold";

    /**
     * Constructor for Pill class.
     *
     * @param text      button text
     * @param bgColor   background color
     * @param textColor text color
     */
    public Pill(String text, String bgColor, String textColor) {
        setText(text);
        setStyle(String.format("%s;-fx-background-color:%s;-fx-text-fill:%s;", DEFAULT_STYLE, bgColor, textColor));
    }

    /**
     * Constructor for Pill class.
     *
     * @param text      button text
     * @param bgColor   background color
     * @param textColor text color
     * @param fontSize  font size
     */
    public Pill(String text, String bgColor, String textColor, int fontSize) {
        this(text, bgColor, textColor);
        setStyle(getStyle() + String.format("-fx-font-size: %s", fontSize));
    }
}
