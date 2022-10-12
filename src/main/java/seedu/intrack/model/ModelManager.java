package seedu.intrack.model;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.intrack.commons.core.GuiSettings;
import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.model.internship.Internship;

/**
 * Represents the in-memory model of the internship tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InTrack inTrack;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given inTrack and userPrefs.
     */
    public ModelManager(ReadOnlyInTrack inTrack, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(inTrack, userPrefs);

        logger.fine("Initializing with internship tracker: " + inTrack + " and user prefs " + userPrefs);

        this.inTrack = new InTrack(inTrack);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.inTrack.getInternshipList());
    }

    public ModelManager() {
        this(new InTrack(), new UserPrefs());
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
    public Path getInTrackFilePath() {
        return userPrefs.getInTrackFilePath();
    }

    @Override
    public void setInTrackFilePath(Path inTrackFilePath) {
        requireNonNull(inTrackFilePath);
        userPrefs.setInTrackFilePath(inTrackFilePath);
    }

    //=========== InTrack ================================================================================

    @Override
    public void setInTrack(ReadOnlyInTrack inTrack) {
        this.inTrack.resetData(inTrack);
    }

    @Override
    public ReadOnlyInTrack getInTrack() {
        return inTrack;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return inTrack.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        inTrack.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        inTrack.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        inTrack.setInternship(target, editedInternship);
    }

    //=========== Filtered Internship List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedInTrack}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return filteredInternships;
    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        filteredInternships.setPredicate(predicate);
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
        return inTrack.equals(other.inTrack)
                && userPrefs.equals(other.userPrefs)
                && filteredInternships.equals(other.filteredInternships);
    }

}
