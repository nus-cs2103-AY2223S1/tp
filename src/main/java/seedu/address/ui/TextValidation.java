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
    private static final String SUCCESS_MESSAGE = "Here are the appointments for the new month :D";
    private static final String ERROR_MESSAGE = "Date should be in the format [dd-MMM-yyyy]";
    private static final String ERROR_STYLE = "-fx-border-color: RED; -fx-border-width: 2;"
            + " -fx-border-radius: 5; -fx-text-fill: RED; -fx-font-size: 13;";
    private static final String SUCCESS_STYLE = "-fx-border-color: GREEN; -fx-border-width: 2;"
            + " -fx-border-radius: 5; -fx-text-fill: GREEN; -fx-font-size: 13;";
    private static final String EMPTY_STYLE = "-fx-border-color: TRANSPARENT; -fx-border-width: 2;"
            + " -fx-border-radius: 5; -fx-text-fill: TRANSPARENT";

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
            textValidation.setText(ERROR_MESSAGE);
            textValidation.setStyle(ERROR_STYLE);
        } else {
            textValidation.setText("");
            textValidation.setStyle(EMPTY_STYLE);
        }
    }

}
