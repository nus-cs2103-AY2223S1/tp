package seedu.foodrem.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.foodrem.views.UiView;

/**
 * A UI for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {
    @FXML private StackPane placeHolder;

    public ResultDisplay() {
        super("ResultDisplay.fxml");
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        new UiView(this).viewFrom(feedbackToUser);
    }

    public void clear() {
        placeHolder.getChildren().clear();
    }

    public void place(Node item) {
        placeHolder.getChildren().add(item);
    }
}
