package gim.ui;

import gim.model.exercise.ExerciseHashMap;
import gim.model.exercise.ExerciseKeys;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the Saved Exercises List Window that is displayed at the Bottom Right of the application.
 */
public class SavedExerciseListWindow extends UiPart<Region> implements Observer {

    private static final String FXML = "SavedExerciseListWindow.fxml";

    private ExerciseKeys exerciseKeys;

    @FXML
    private TextArea savedExerciseList;

    private ExerciseHashMap exerciseHashMap;

    /**
     * Creates a {@code SavedExerciseListWindow}.
     */
    public SavedExerciseListWindow() {
        super(FXML);
    }

    /**
     * Sets a copy of ExerciseHashMap in SavedExerciseListWindow.
     * Subscribes the window to changes in the ExerciseHashmap and displays its current state.
     * @param ehm ExerciseHashMap
     */
    public void setExerciseHashMap(ExerciseHashMap ehm) {
        exerciseHashMap = ehm;
        ehm.addUI(this);
        ehm.notifyObservers();
    }

    /**
     *  Defines the response to be taken when there is a change in the ExerciseHashMap the window is subscribed to.
     */
    @Override
    public void update() {
        exerciseKeys = new ExerciseKeys(exerciseHashMap.getAllKeys());
        savedExerciseList.setText(exerciseKeys.getDisplay());
    }


}
