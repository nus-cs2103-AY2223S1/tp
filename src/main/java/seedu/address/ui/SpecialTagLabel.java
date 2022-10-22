package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.Label;

/**
 * The UI component that is responsible for the styling of the SpecialTag labels.
 */
public class SpecialTagLabel extends Label {


    public static final String LOW_KEYWORD = "LOW";
    public static final String MEDIUM_KEYWORD = "MEDIUM";
    public static final String HIGH_KEYWORD = "HIGH";
    private static final String LOW_COLOR = "green";
    private static final String MEDIUM_COLOR = "orange";
    private static final String HIGH_COLOR = "red";
    private static final String DEFAULT_COLOR = "#3e7b91";
    private static final String TEXT_FILL_COLOR = "-fx-text-fill: white;";
    private static final String BACKGROUND_COLOR = "-fx-background-color: ";
    private static final String PADDING = "-fx-padding: 1 3 1 3;";
    private static final String BORDER_RADIUS = "-fx-border-radius: 2;";
    private static final String BACKGROUND_RADIUS = "-fx-background-radius: 2;";
    private static final String FONT_SIZE = "-fx-font-size: 11;";

    /**
     * Creates a {@code Label} with the given {@code content} to
     * be displayed.
     */
    public SpecialTagLabel(String content) {
        super(content);
        requireNonNull(content);
        switch (content) {
        case HIGH_KEYWORD:
            setLabelStyle(HIGH_COLOR);
            break;
        case MEDIUM_KEYWORD:
            setLabelStyle(MEDIUM_COLOR);
            break;
        case LOW_KEYWORD:
            setLabelStyle(LOW_COLOR);
            break;
        default:
            setLabelStyle(DEFAULT_COLOR);
        }
    }

    private void setLabelStyle(String color) {
        this.setStyle(TEXT_FILL_COLOR
                + BACKGROUND_COLOR + color.toLowerCase() + ";" + PADDING + BORDER_RADIUS
                + BACKGROUND_RADIUS + FONT_SIZE);
    }
}
