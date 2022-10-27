package jeryl.fyp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for displaying the
 * title of the uncompleted student list panel.
 */
public class UncompletedStudentListTitle extends UiPart<Region> {

    private static final String FXML = "UncompletedStudentListTitle.fxml";

    @FXML
    private Label uncompletedTitleLabel;

    /**
     * Creates a {@code UncompletedStudentListTitle}.
     */
    public UncompletedStudentListTitle() {
        super(FXML);
        uncompletedTitleLabel.setText("Not Completed");
    }
}
