package seedu.workbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.workbook.commons.core.GuiSettings;
import seedu.workbook.commons.core.LogsCenter;
import seedu.workbook.model.internship.Internship;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedWorkBook versionedWorkBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given workBook and userPrefs.
     */
    public ModelManager(ReadOnlyWorkBook workBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(workBook, userPrefs);

        logger.fine("Initializing with work book: " + workBook + " and user prefs " + userPrefs);

        versionedWorkBook = new VersionedWorkBook(workBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.versionedWorkBook.getInternshipList());
    }

    public ModelManager() {
        this(new WorkBook(), new UserPrefs());
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
    public Path getWorkBookFilePath() {
        return userPrefs.getWorkBookFilePath();
    }

    @Override
    public void setWorkBookFilePath(Path workBookFilePath) {
        requireNonNull(workBookFilePath);
        userPrefs.setWorkBookFilePath(workBookFilePath);
    }

    //=========== WorkBook ================================================================================

    @Override
    public void setWorkBook(ReadOnlyWorkBook workBook) {
        this.versionedWorkBook.resetData(workBook);
    }

    @Override
    public ReadOnlyWorkBook getWorkBook() {
        return versionedWorkBook;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return versionedWorkBook.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        versionedWorkBook.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        versionedWorkBook.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        versionedWorkBook.setInternship(target, editedInternship);
    }

    //=========== Filtered Internship List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedWorkBook}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return filteredInternships;
    }

    @Override
    public boolean canUndoWorkBook() {
        return versionedWorkBook.canUndo();
    }
    @Override
    public void undoWorkBook() {
        versionedWorkBook.undo();
    }

    @Override
    public void commitWorkBook() {
        versionedWorkBook.commit();
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
        return versionedWorkBook.equals(other.versionedWorkBook)
                && userPrefs.equals(other.userPrefs)
                && filteredInternships.equals(other.filteredInternships);
    }

}
