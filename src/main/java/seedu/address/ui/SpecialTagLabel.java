package seedu.address.ui;


import javafx.scene.control.Label;

public class SpecialTagLabel extends Label {

    private static final String DEFAULT_COLOR = "#3e7b91";
    private static final String LOW_COLOR = "green";
    private static final String MEDIUM_COLOR = "orange";
    private static final String HIGH_COLOR = "red";
    private static final String TEXT_FILL_COLOR = "-fx-text-fill: white;";
    private static final String BACKGROUND_COLOR = "-fx-background-color: ";
    private static final String PADDING = "-fx-padding: 1 3 1 3;";
    private static final String BORDER_RADIUS = "-fx-border-radius: 2;";
    private static final String BACKGROUND_RADIUS = "-fx-background-radius: 2;";
    private static final String FONT_SIZE = "-fx-font-size: 11;";

    public SpecialTagLabel(String content) {
        super(content);
        switch (content) {
        case "HIGH":
            setLabelStyle(HIGH_COLOR);
            break;
        case "MEDIUM":
            setLabelStyle(MEDIUM_COLOR);
            break;
        case "LOW":
            setLabelStyle(LOW_COLOR);
            break;
        default:
            setLabelStyle(DEFAULT_COLOR);
        }
    }

    private void setLabelStyle(String color) {
        this.setStyle(TEXT_FILL_COLOR +
                BACKGROUND_COLOR + color + ";" +
                PADDING +
                BORDER_RADIUS +
                BACKGROUND_RADIUS +
                FONT_SIZE);
    }
}
