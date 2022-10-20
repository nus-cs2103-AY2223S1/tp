package gim.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the Saved Exercises List Window that is displayed at the Bottom Right of the application.
 */
public class SavedExerciseListWindow extends UiPart<Region> {

    private static final String FXML = "SavedExerciseListWindow.fxml";

    @FXML
    private TextArea savedExerciseList;

    /**
     * Creates a {@code SavedExerciseListWindow}.
     */
    public SavedExerciseListWindow() {
        super(FXML); }

}
