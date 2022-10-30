package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.internship.Internship;

/**
 * Represents the in-memory model of findMyIntern data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FindMyIntern findMyIntern;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;
    private SortedList<Internship> sortedInternships;
    private Predicate<Internship> currentPredicate = PREDICATE_SHOW_ALL_INTERNSHIPS;

    /**
     * Initializes a ModelManager with the given findMyIntern and userPrefs.
     */
    public ModelManager(ReadOnlyFindMyIntern findMyIntern, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(findMyIntern, userPrefs);

        logger.fine("Initializing with findMyIntern: " + findMyIntern + " and user prefs " + userPrefs);

        this.findMyIntern = new FindMyIntern(findMyIntern);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.findMyIntern.getInternshipList());
        sortedInternships = new SortedList<>(filteredInternships);
    }

    public ModelManager() {
        this(new FindMyIntern(), new UserPrefs());
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
    public Path getFindMyInternFilePath() {
        return userPrefs.getFindMyInternFilePath();
    }

    @Override
    public void setFindMyInternFilePath(Path findMyInternFilePath) {
        requireNonNull(findMyInternFilePath);
        userPrefs.setFindMyInternFilePath(findMyInternFilePath);
    }

    //=========== FindMyIntern ================================================================================

    @Override
    public void setFindMyIntern(ReadOnlyFindMyIntern findMyIntern) {
        this.findMyIntern.resetData(findMyIntern);
    }

    @Override
    public ReadOnlyFindMyIntern getFindMyIntern() {
        return findMyIntern;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return findMyIntern.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        findMyIntern.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        findMyIntern.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        findMyIntern.setInternship(target, editedInternship);
    }

    @Override
    public void markInternship(Internship target, Internship markInternship) {
        setInternship(target, markInternship);
    }

    //=========== Filtered Internship List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedFindMyIntern}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return sortedInternships;
    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        currentPredicate = predicate;
        filteredInternships.setPredicate(predicate);
    }

    @Override
    public Predicate<Internship> getCurrentPredicate() {
        return currentPredicate;
    }

    @Override
    public void updateSortedInternshipList(Comparator<Internship> comparator) {
        requireNonNull(comparator);
        sortedInternships.setComparator(comparator);
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
        return findMyIntern.equals(other.findMyIntern)
                && userPrefs.equals(other.userPrefs)
                && sortedInternships.equals(other.sortedInternships);
    }

}
