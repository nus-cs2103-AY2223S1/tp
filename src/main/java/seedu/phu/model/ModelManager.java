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

/**
 * Represents the in-memory model of the internship book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternshipBook internshipBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given internshipBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternshipBook internshipBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internshipBook, userPrefs);

        logger.fine("Initializing with internship book: " + internshipBook + " and user prefs " + userPrefs);

        this.internshipBook = new InternshipBook(internshipBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.internshipBook.getInternshipList());
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
        this.internshipBook.resetData(internshipBook);
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return internshipBook;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internshipBook.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        internshipBook.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        internshipBook.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        internshipBook.setInternship(target, editedInternship);
    }

    @Override
    public void viewInternship(Internship internship) {
        List<Internship> internshipList = new ArrayList<>();
        internshipList.add(internship);

        updateFilteredInternshipList(new ExactMatchPredicate(internshipList));
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
    public void sortList(ComparableCategory category) {
        requireNonNull(category);

        internshipBook.sortInternshipList(new Comparator<Internship>() {
            @Override
            public int compare(Internship p1, Internship p2) {
                return p1.compareTo(p2, category);
            }
        });
    }

    @Override
    public void reverseList() {
        internshipBook.reverseList();
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
        return internshipBook.equals(other.internshipBook)
                && userPrefs.equals(other.userPrefs)
                && filteredInternships.equals(other.filteredInternships);
    }

}
