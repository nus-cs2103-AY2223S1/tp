package jeryl.fyp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for displaying the
 * title of the completed student list panel.
 */
public class CompletedStudentListTitle extends UiPart<Region> {

    private static final String FXML = "CompletedStudentListTitle.fxml";

    @FXML
    private Label completedTitleLabel;

    /**
     * Creates a {@code CompletedStudentListTitle}.
     */
    public CompletedStudentListTitle() {
        super(FXML);
        completedTitleLabel.setText("Completed");
    }
}
