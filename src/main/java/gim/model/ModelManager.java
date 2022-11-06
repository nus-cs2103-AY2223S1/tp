package gim.model;

import static gim.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import gim.commons.core.GuiSettings;
import gim.commons.core.LogsCenter;
import gim.model.exercise.Exercise;
import gim.model.exercise.ExerciseHashMap;
import gim.model.exercise.Name;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the exercise tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ExerciseTracker exerciseTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Exercise> filteredExercises;

    /**
     * Initializes a ModelManager with the given exerciseTracker and userPrefs.
     */
    public ModelManager(ReadOnlyExerciseTracker exerciseTracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(exerciseTracker, userPrefs);

        logger.fine("Initializing with exercise tracker: " + exerciseTracker + " and user prefs " + userPrefs);

        this.exerciseTracker = new ExerciseTracker(exerciseTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExercises = new FilteredList<>(this.exerciseTracker.getExerciseList());
    }

    public ModelManager() {
        this(new ExerciseTracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getExerciseTrackerFilePath() {
        return userPrefs.getExerciseTrackerFilePath();
    }

    @Override
    public void setExerciseTrackerFilePath(Path exerciseTrackerFilePath) {
        requireNonNull(exerciseTrackerFilePath);
        userPrefs.setExerciseTrackerFilePath(exerciseTrackerFilePath);
    }

    //=========== ExerciseTracker ================================================================================

    @Override
    public void setExerciseTracker(ReadOnlyExerciseTracker exerciseTracker) {
        this.exerciseTracker.resetData(exerciseTracker);
    }

    @Override
    public ReadOnlyExerciseTracker getExerciseTracker() {
        return exerciseTracker;
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exerciseTracker.hasExercise(exercise);
    }

    @Override
    public void deleteExercise(Exercise target) {
        exerciseTracker.removeExercise(target);
    }

    @Override
    public Exercise getExercisePR(Name exerciseName) {
        return exerciseTracker.getExercisePR(exerciseName);
    }

    @Override
    public ArrayList<Exercise> getAllExercisePRs() {
        return exerciseTracker.getAllExercisePRs();
    }

    @Override
    public Exercise addExercise(Exercise exercise) {
        Exercise added = exerciseTracker.addExercise(exercise);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        return added;
    }

    //=========== Filtered Exercise List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Exercise} backed by the internal list of
     * {@code versionedExerciseTracker}
     */
    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return filteredExercises;
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        resetDisplayedList();
        filteredExercises.setPredicate(predicate);
    }

    @Override
    public void filterFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        FilteredList<Exercise> filteredList = new FilteredList<>(this.exerciseTracker.getDuplicatedDisplayedList());
        filteredList.setPredicate(predicate);
        exerciseTracker.filterDisplayedList(filteredList);
    }

    @Override
    public void sortFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        exerciseTracker.sortDisplayedList();
        FilteredList<Exercise> filteredList = new FilteredList<>(this.exerciseTracker.getDuplicatedDisplayedList());
        filteredList.setPredicate(predicate);
        exerciseTracker.filterDisplayedList(filteredList);
    }

    @Override
    public void resetDisplayedList() {
        exerciseTracker.resetDisplayedList();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return exerciseTracker.equals(other.exerciseTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredExercises.equals(other.filteredExercises);
    }

    public ExerciseHashMap getExerciseHashMap() {
        return exerciseTracker.getExerciseHashMap();
    }
}
