package seedu.application.model;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;
import seedu.application.model.application.interview.InterviewComparator;

/**
 * Represents the in-memory model of the application book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final VersionedApplicationBook versionedApplicationBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Application> filteredApplications;
    private final FilteredList<Application> filteredApplicationsWithUpcomingInterviews;
    private final SortedList<Application> sortedFilteredApplications;
    private final ObservableList<Application> applicationsWithInterview;

    /**
     * Initializes a ModelManager with the given versionedApplicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyApplicationBook applicationBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(applicationBook, userPrefs);

        logger.fine("Initializing with application book: " + applicationBook + " and user prefs " + userPrefs);

        versionedApplicationBook = new VersionedApplicationBook(applicationBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplications = initialiseFilteredList(this.versionedApplicationBook);
        sortedFilteredApplications = new SortedList<>(filteredApplications);
        applicationsWithInterview = filterApplicationsWithInterview();
        filteredApplicationsWithUpcomingInterviews = new FilteredList<>(applicationsWithInterview);
    }

    public ModelManager() {
        this(new ApplicationBook(), new UserPrefs());
    }


    private static FilteredList<Application> initialiseFilteredList(VersionedApplicationBook versionedApplicationBook) {
        FilteredList<Application> initialList = new FilteredList<>(versionedApplicationBook.getApplicationList());
        initialList.setPredicate(HIDE_ARCHIVE_IN_LIST);
        return initialList;
    }

    private ObservableList<Application> filterApplicationsWithInterview() {
        ObservableList<Application> applicationsWithInterview = FXCollections.observableList(
            versionedApplicationBook
                        .getApplicationList()
                        .stream()
                        .filter(application -> application.getInterview().isPresent() && !application.isArchived())
                        .collect(Collectors.toList()));
        applicationsWithInterview.sort(new InterviewComparator());
        return applicationsWithInterview;
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
    public Path getApplicationBookFilePath() {
        return userPrefs.getApplicationBookFilePath();
    }

    @Override
    public void setApplicationBookFilePath(Path applicationBookFilePath) {
        requireNonNull(applicationBookFilePath);
        userPrefs.setApplicationBookFilePath(applicationBookFilePath);
    }

    //=========== ApplicationBook ================================================================================

    @Override
    public void setApplicationBook(ReadOnlyApplicationBook applicationBook) {
        versionedApplicationBook.resetData(applicationBook);
        commitApplicationBook();
    }

    @Override
    public ReadOnlyApplicationBook getApplicationBook() {
        return versionedApplicationBook;
    }

    @Override
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return versionedApplicationBook.hasApplication(application);
    }

    @Override
    public boolean hasSameInterviewTime(Application application) {
        requireNonNull(application);
        return versionedApplicationBook.hasSameInterviewTime(application);
    }

    @Override
    public boolean hasSameInterviewTime(Interview interview) {
        requireNonNull(interview);
        return versionedApplicationBook.hasSameInterviewTime(interview);
    }

    @Override
    public boolean hasSameInterviewTimeExcludeSelf(Interview interview, Application application) {
        requireNonNull(interview);
        return versionedApplicationBook.hasSameInterviewTimeExcludeSelf(interview, application);
    }

    @Override
    public void deleteApplication(Application target) {
        versionedApplicationBook.removeApplication(target);
        commitApplicationBook();
    }

    @Override
    public void addApplication(Application application) {
        versionedApplicationBook.addApplication(application);
        filteredApplications.setPredicate(HIDE_ARCHIVE_IN_LIST);
        commitApplicationBook();
    }

    @Override
    public void archiveApplication(Application target) {
        versionedApplicationBook.setArchive(target);
        filteredApplications.setPredicate(HIDE_ARCHIVE_IN_LIST);
        commitApplicationBook();
    }

    @Override
    public void retrieveApplication(Application target) {
        versionedApplicationBook.retrieveApplication(target);
        filteredApplications.setPredicate(SHOW_ARCHIVE_ONLY);
        commitApplicationBook();
    }

    @Override
    public void setApplication(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        versionedApplicationBook.setApplication(target, editedApplication);
        commitApplicationBook();
    }

    //=========== Sorted, Filtered Application List Accessors ======================================================

    /**
     * Returns an unmodifiable view of the list of {@code Application} backed by the internal list of
     * {@code versionedApplicationBook}
     */
    @Override
    public ObservableList<Application> getFilteredApplicationList() {
        return sortedFilteredApplications;
    }

    @Override
    public ObservableList<Application> getFilteredApplicationsWithUpcomingInterviewList() {
        return filteredApplicationsWithUpcomingInterviews.sorted(new InterviewComparator());
    }

    @Override
    public ObservableList<Application> getApplicationListWithInterview() {
        return applicationsWithInterview;
    }

    @Override
    public void updateFilteredApplicationList(Predicate<Application> predicate) {
        requireNonNull(predicate);
        filteredApplications.setPredicate(predicate);
    }

    @Override
    public void updateFilteredApplicationsWithUpcomingInterviewList(Predicate<Application> predicate) {
        requireNonNull(predicate);
        filteredApplicationsWithUpcomingInterviews.setPredicate(predicate);
    }

    //=========== Undo & Redo =====================================================================================

    @Override
    public void commitApplicationBook() {
        versionedApplicationBook.commit();
    }

    @Override
    public boolean canUndoApplicationBook() {
        return versionedApplicationBook.canUndo();
    }

    @Override
    public boolean canRedoApplicationBook() {
        return versionedApplicationBook.canRedo();
    }

    @Override
    public void undoApplicationBook() {
        versionedApplicationBook.undo();
    }

    @Override
    public void redoApplicationBook() {
        versionedApplicationBook.redo();
    }

    /**
     * Updates the interview list.
     */
    @Override
    public void updateApplicationListWithInterview() {
        applicationsWithInterview.clear();
        applicationsWithInterview.addAll(filteredApplications);
        applicationsWithInterview.removeIf(application -> application.getInterview().isEmpty());
        applicationsWithInterview.sort(new InterviewComparator());
    }

    @Override
    public void sortApplicationListByCompany(boolean shouldReverse) {
        Comparator<Application> comparator = Comparator.comparing(Application::getCompany);
        if (shouldReverse) {
            comparator = comparator.reversed();
        }
        sortedFilteredApplications.setComparator(comparator);
    }

    @Override
    public void sortApplicationListByPosition(boolean shouldReverse) {
        Comparator<Application> comparator = Comparator.comparing(Application::getPosition);
        if (shouldReverse) {
            comparator = comparator.reversed();
        }
        sortedFilteredApplications.setComparator(comparator);
    }

    @Override
    public void sortApplicationListByDate(boolean shouldReverse) {
        Comparator<Application> comparator = Comparator.comparing(Application::getDate);
        if (shouldReverse) {
            comparator = comparator.reversed();
        }
        sortedFilteredApplications.setComparator(comparator);
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
        return versionedApplicationBook.equals(other.versionedApplicationBook)
                && userPrefs.equals(other.userPrefs)
                && sortedFilteredApplications.equals(other.sortedFilteredApplications);
    }

}
