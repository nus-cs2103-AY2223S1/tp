package hobbylist.ui;

import static java.util.Objects.requireNonNull;

import java.awt.ScrollPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;



/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private Label instead;
    @FXML
    private ScrollPane placeHolder;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        instead.setText(feedbackToUser);

    }

}
