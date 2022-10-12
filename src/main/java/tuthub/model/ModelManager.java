package tuthub.model;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import tuthub.commons.core.GuiSettings;
import tuthub.commons.core.LogsCenter;
import tuthub.model.tutor.Tutor;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Tuthub tuthub;
    private final UserPrefs userPrefs;
    private final FilteredList<Tutor> filteredTutors;

    /**
     * Initializes a ModelManager with the given tuthub and userPrefs.
     */
    public ModelManager(ReadOnlyTuthub tuthub, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tuthub, userPrefs);

        logger.fine("Initializing with address book: " + tuthub + " and user prefs " + userPrefs);

        this.tuthub = new Tuthub(tuthub);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTutors = new FilteredList<>(this.tuthub.getTutorList());
    }

    public ModelManager() {
        this(new Tuthub(), new UserPrefs());
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
    public Path getTuthubFilePath() {
        return userPrefs.getTuthubFilePath();
    }

    @Override
    public void setTuthubFilePath(Path tuthubFilePath) {
        requireNonNull(tuthubFilePath);
        userPrefs.setTuthubFilePath(tuthubFilePath);
    }

    //=========== Tuthub ================================================================================

    @Override
    public void setTuthub(ReadOnlyTuthub tuthub) {
        this.tuthub.resetData(tuthub);
    }

    @Override
    public ReadOnlyTuthub getTuthub() {
        return tuthub;
    }

    @Override
    public boolean hasTutor(Tutor tutor) {
        requireNonNull(tutor);
        return tuthub.hasTutor(tutor);
    }

    @Override
    public void deleteTutor(Tutor target) {
        tuthub.removeTutor(target);
    }

    @Override
    public void addTutor(Tutor tutor) {
        tuthub.addTutor(tutor);
        updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
    }

    @Override
    public void setTutor(Tutor target, Tutor editedTutor) {
        requireAllNonNull(target, editedTutor);

        tuthub.setTutor(target, editedTutor);
    }

    //=========== Filtered Tutor List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Tutor} backed by the internal list of
     * {@code versionedTuthub}
     */
    @Override
    public ObservableList<Tutor> getFilteredTutorList() {
        return filteredTutors;
    }

    @Override
    public void updateFilteredTutorList(Predicate<Tutor> predicate) {
        requireNonNull(predicate);
        filteredTutors.setPredicate(predicate);
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
        return tuthub.equals(other.tuthub)
                && userPrefs.equals(other.userPrefs)
                && filteredTutors.equals(other.filteredTutors);
    }

}
