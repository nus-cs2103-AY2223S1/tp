package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A Label that handles the errors from the jump feature
 * when interacted with.
 */
public class TextValidation extends UiPart<Region> {
    private static final String FXML = "TextValidation.fxml";
    private static final String EMPTY_STRING = "";
    private static final String SUCCESS_MESSAGE = "Here are the appointments for the new month";
    private static final String WRONG_FORMAT_MESSAGE = "Date should be in the format [d-Mmm-yyyy]";
    private static final String INVALID_VALUE_MESSAGE = "Input day does not exist for that month";
    private static final String EMPTY_STYLE = "-fx-border-color: TRANSPARENT; -fx-border-width: 2;"
            + " -fx-border-radius: 5; -fx-text-fill: TRANSPARENT";
    private static final String SUCCESS_STYLE = "-fx-border-color: GREEN; -fx-border-width: 2;"
            + " -fx-border-radius: 5; -fx-text-fill: GREEN; -fx-font-size: 13;";
    private static final String WRONG_FORMAT_STYLE = "-fx-border-color: RED; -fx-border-width: 2;"
            + " -fx-border-radius: 5; -fx-text-fill: RED; -fx-font-size: 13;";

    @FXML
    private Label textValidation;

    /**
     * Creates a {@code TextValidation}.
     */
    public TextValidation() {
        super(FXML);
    }

    public void setTextValidation(String validation) {

        if (validation.equals("success")) {
            textValidation.setText(SUCCESS_MESSAGE);
            textValidation.setStyle(SUCCESS_STYLE);
        } else if (validation.equals("failure")) {
            textValidation.setText(WRONG_FORMAT_MESSAGE);
            textValidation.setStyle(WRONG_FORMAT_STYLE);
        } else if (validation.equals("invalid")) {
            textValidation.setText(INVALID_VALUE_MESSAGE);
            textValidation.setStyle(WRONG_FORMAT_STYLE);
        } else {
            textValidation.setText(EMPTY_STRING);
            textValidation.setStyle(EMPTY_STYLE);
        }
    }

}
