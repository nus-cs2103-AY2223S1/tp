package seedu.phu.model;

import static java.util.Objects.requireNonNull;
import static seedu.phu.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.phu.commons.core.GuiSettings;
import seedu.phu.commons.core.LogsCenter;
import seedu.phu.model.internship.ComparableCategory;
import seedu.phu.model.internship.ExactMatchPredicate;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.NameContainsKeywordsPredicate;

/**
 * Represents the in-memory model of the internship book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedInternshipBook versionedInternshipBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;
    private final FilteredList<Internship> viewItem;

    /**
     * Initializes a ModelManager with the given internshipBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternshipBook internshipBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internshipBook, userPrefs);

        logger.fine("Initializing with internship book: " + internshipBook + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        versionedInternshipBook = new VersionedInternshipBook(internshipBook);
        filteredInternships = new FilteredList<>(versionedInternshipBook.getInternshipList());
        viewItem = new FilteredList<>(versionedInternshipBook.getInternshipList());
        updateViewItem(new NameContainsKeywordsPredicate(new ArrayList<>()));
    }

    public ModelManager() {
        this(new InternshipBook(), new UserPrefs());
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
    public Path getInternshipBookFilePath() {
        return userPrefs.getInternshipBookFilePath();
    }

    @Override
    public void setInternshipBookFilePath(Path internshipBookFilePath) {
        requireNonNull(internshipBookFilePath);
        userPrefs.setInternshipBookFilePath(internshipBookFilePath);
    }

    //=========== InternshipBook ================================================================================

    @Override
    public void setInternshipBook(ReadOnlyInternshipBook internshipBook) {
        versionedInternshipBook.resetData(internshipBook);
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return versionedInternshipBook;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return versionedInternshipBook.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        versionedInternshipBook.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        versionedInternshipBook.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        versionedInternshipBook.setInternship(target, editedInternship);
    }

    @Override
    public void viewInternship(Internship internship) {
        List<Internship> internshipList = new ArrayList<>();
        internshipList.add(internship);

        updateViewItem(new ExactMatchPredicate(internshipList));
    }

    //=========== Filtered Internship List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedInternshipBook}
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
    public ObservableList<Internship> getViewItem() {
        return viewItem;
    }

    @Override
    public void updateViewItem(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        viewItem.setPredicate(predicate);
    }

    @Override
    public void sortList(ComparableCategory category) {
        requireNonNull(category);

        versionedInternshipBook.sortInternshipList(new Comparator<Internship>() {
            @Override
            public int compare(Internship p1, Internship p2) {
                return p1.compareTo(p2, category);
            }
        });
    }

    @Override
    public void reverseList() {
        versionedInternshipBook.reverseList();
    }

    //=========== Handle undo and redo commands =============================================================

    @Override
    public boolean canUndoInternshipBook() {
        return versionedInternshipBook.canUndo();
    }

    @Override
    public boolean canRedoInternshipBook() {
        return versionedInternshipBook.canRedo();
    }

    @Override
    public void undoInternshipBook() {
        versionedInternshipBook.undo();
    }

    @Override
    public void redoInternshipBook() {
        versionedInternshipBook.redo();
    }

    @Override
    public void commitInternshipBookChange() {
        versionedInternshipBook.commitChange();
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
        return versionedInternshipBook.equals(other.versionedInternshipBook)
                && userPrefs.equals(other.userPrefs)
                && filteredInternships.equals(other.filteredInternships);
    }

}
