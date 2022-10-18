package gim.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the Saved Exercises List that is displayed at the footer of the application.
 */
public class SavedExerciseListWindow extends UiPart<Region> {

    private static final String FXML = "SavedExerciseListWindow.fxml";

    @FXML
    private TextArea savedExerciseList;

    /**
     * Creates a {@code SavedExerciseList}.
     */
    public SavedExerciseListWindow() {
        super(FXML);
        savedExerciseList.setText("testing");
    }

}
