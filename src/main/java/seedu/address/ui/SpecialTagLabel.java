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
    public static final String POTENTIAL_KEYWORD = "POTENTIAL";
    public static final String CURRENT_KEYWORD = "CURRENT";
    private static final String LOW_COLOR = "green";
    private static final String MEDIUM_COLOR = "orange";
    private static final String HIGH_COLOR = "red";
    private static final String POTENTIAL_COLOR = "blue";
    private static final String CURRENT_COLOR = "grey";
    private static final String DEFAULT_COLOR = "#d91175";
    private static final String TEXT_FILL_COLOR = "-fx-text-fill: white;";
    private static final String BACKGROUND_COLOR = "-fx-background-color: ";
    private static final String PADDING = "-fx-padding: 4 3 4 3;";
    private static final String BORDER_RADIUS = "-fx-border-radius: 2;";
    private static final String BACKGROUND_RADIUS = "-fx-background-radius: 8;";
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
        case POTENTIAL_KEYWORD:
            setLabelStyle(POTENTIAL_COLOR);
            break;
        case CURRENT_KEYWORD:
            setLabelStyle(CURRENT_COLOR);
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
