package gim.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import gim.commons.core.GuiSettings;
import gim.model.exercise.Exercise;
import gim.model.exercise.ExerciseHashMap;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Exercise> PREDICATE_SHOW_ALL_EXERCISES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' exercise tracker file path.
     */
    Path getExerciseTrackerFilePath();

    /**
     * Sets the user prefs' exercise tracker file path.
     */
    void setExerciseTrackerFilePath(Path exerciseTrackerFilePath);

    /**
     * Replaces exercise tracker data with the data in {@code exerciseTracker}.
     */
    void setExerciseTracker(ReadOnlyExerciseTracker exerciseTracker);

    /** Returns the ExerciseTracker */
    ReadOnlyExerciseTracker getExerciseTracker();

    /**
     * Returns true if an exercise with the same identity as {@code exercise} exists in the exercise tracker.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Deletes the given exercise.
     * The exercise must exist in the exercise tracker.
     */
    void deleteExercise(Exercise target);

    /**
     * Adds the given exercise.
     * {@code exercise} can already exist in the exercise tracker.
     */
    Exercise addExercise(Exercise exercise);

    /**
     * Replaces the given exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the exercise tracker.
     * The exercise identity of {@code editedExercise} can be the same as another existing exercise
     * in the exercise tracker.
     */
    void setExercise(Exercise target, Exercise editedExercise);

    /** Returns an unmodifiable view of the filtered exercise list */
    ObservableList<Exercise> getFilteredExerciseList();
    /** Returns a copy of the hashmap of Exercises stored. */
    ExerciseHashMap getExerciseHashMap();

    /**
     * Updates the filter of the filtered exercise list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);

}
