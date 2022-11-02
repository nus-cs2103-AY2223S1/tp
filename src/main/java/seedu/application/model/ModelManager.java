package seedu.application.model;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.application.Application;
import seedu.application.model.application.UpcomingInterviewPredicate;
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
    private final SortedList<Application> sortedFilteredApplications;
    private final FilteredList<Application> applicationsWithInterview;
    private final ObservableList<Application> applicationsWithUpcomingInterviews;

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
        initialiseSortOrder();
        applicationsWithInterview = filterApplicationsWithInterview().filtered(Model.HIDE_ARCHIVE_IN_LIST);
        applicationsWithUpcomingInterviews = filterApplicationsWithUpcomingInterview();
    }

    public ModelManager() {
        this(new ApplicationBook(), new UserPrefs());
    }


    private static FilteredList<Application> initialiseFilteredList(VersionedApplicationBook versionedApplicationBook) {
        FilteredList<Application> initialList = new FilteredList<>(versionedApplicationBook.getApplicationList());
        initialList.setPredicate(HIDE_ARCHIVE_IN_LIST);
        return initialList;
    }

    private void initialiseSortOrder() {
        SortSetting sortSetting = getSortSetting();
        requireNonNull(sortSetting);
        switch (sortSetting) {
        case BY_COMPANY:
            sortApplicationListByCompany(false);
            break;
        case BY_COMPANY_REVERSE:
            sortApplicationListByCompany(true);
            break;
        case BY_POSITION:
            sortApplicationListByPosition(false);
            break;
        case BY_POSITION_REVERSE:
            sortApplicationListByPosition(true);
            break;
        case BY_DATE:
            sortApplicationListByDate(false);
            break;
        case BY_DATE_REVERSE:
            sortApplicationListByDate(true);
            break;
        case BY_INTERVIEW:
            sortApplicationListByInterview(false);
            break;
        case BY_INTERVIEW_REVERSE:
            sortApplicationListByInterview(true);
            break;
        default:
            // Execution should not reach here since every enum value (and null) has been handled.
            // Only way execution can reach here is if a new enum value was added but this switch statement
            // was not updated.
            throw new RuntimeException("Unknown SortSetting found while initialising ModelManager.");
        }

    }

    private ObservableList<Application> filterApplicationsWithInterview() {
        return versionedApplicationBook.getApplicationList()
                .filtered(Application::hasInterview)
                .sorted(new InterviewComparator());
    }

    private ObservableList<Application> filterApplicationsWithUpcomingInterview() {
        return versionedApplicationBook.getApplicationList()
                .filtered(application -> application.hasInterview() && !application.isArchived())
                .filtered(new UpcomingInterviewPredicate())
                .sorted(new InterviewComparator());
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

    @Override
    public SortSetting getSortSetting() {
        return userPrefs.getSortSetting();
    }

    private void setSortSetting(SortSetting sortSetting) {
        requireNonNull(sortSetting);
        userPrefs.setSortSetting(sortSetting);
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
        assert !target.isArchived();
        versionedApplicationBook.setArchive(target);
        filteredApplications.setPredicate(HIDE_ARCHIVE_IN_LIST);
        commitApplicationBook();
    }

    @Override
    public void retrieveApplication(Application target) {
        assert target.isArchived();
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
    public ObservableList<Application> getApplicationsWithUpcomingInterviewList() {
        return applicationsWithUpcomingInterviews;
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
    public void updateApplicationListWithInterview(Predicate<Application> predicate) {
        requireNonNull(predicate);
        applicationsWithInterview.setPredicate(predicate);
    }

    @Override
    public ObservableList<Application> getAllApplicationsInBook() {
        return versionedApplicationBook.getApplicationList();
    }

    @Override
    public void sortApplicationListByCompany(boolean shouldReverse) {
        Comparator<Application> comparator = Comparator.comparing(Application::getCompany);
        if (shouldReverse) {
            comparator = comparator.reversed();
        }
        sortedFilteredApplications.setComparator(comparator);
        setSortSetting(shouldReverse ? SortSetting.BY_COMPANY_REVERSE : SortSetting.BY_COMPANY);
    }

    @Override
    public void sortApplicationListByPosition(boolean shouldReverse) {
        Comparator<Application> comparator = Comparator.comparing(Application::getPosition);
        if (shouldReverse) {
            comparator = comparator.reversed();
        }
        sortedFilteredApplications.setComparator(comparator);
        setSortSetting(shouldReverse ? SortSetting.BY_POSITION_REVERSE : SortSetting.BY_POSITION);
    }

    @Override
    public void sortApplicationListByDate(boolean shouldReverse) {
        Comparator<Application> comparator = Comparator.comparing(Application::getDate);
        if (shouldReverse) {
            comparator = comparator.reversed();
        }
        sortedFilteredApplications.setComparator(comparator);
        setSortSetting(shouldReverse ? SortSetting.BY_DATE_REVERSE : SortSetting.BY_DATE);
    }

    @Override
    public void sortApplicationListByInterview(boolean shouldReverse) {
        Comparator<Application> comparator = (application1, application2) -> {
            // Applications without interviews always compare greater than those with interviews
            if (!application1.hasInterview()) {
                return application2.hasInterview() ? 1 : 0;
            }
            if (!application2.hasInterview()) {
                return -1;
            }

            Comparator<Application> interviewComparator = new InterviewComparator();
            if (shouldReverse) {
                interviewComparator = interviewComparator.reversed();
            }
            return interviewComparator.compare(application1, application2);
        };

        sortedFilteredApplications.setComparator(comparator);
        setSortSetting(shouldReverse ? SortSetting.BY_INTERVIEW_REVERSE : SortSetting.BY_INTERVIEW);
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
