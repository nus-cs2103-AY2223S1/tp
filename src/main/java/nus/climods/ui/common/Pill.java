package nus.climods.ui.common;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Pill extends Button {

    private final static String DEFAULT_STYLE = "-fx-border-width:0;-fx-background-radius: 7px;-fx-padding: 3;"
        + "-fx-font-size: 10;-fx-font-weight:bold";

    public Pill(String text, String bgColor, String textColor) {
        setText(text);
        setStyle(String.format("%s;-fx-background-color:%s;-fx-text-fill:%s;", DEFAULT_STYLE, bgColor, textColor));
    }

    public Pill(String text, String bgColor, String textColor, int fontSize) {
        this(text, bgColor, textColor);
        setFont(new Font(getFont().getName(), fontSize));
    }
}
