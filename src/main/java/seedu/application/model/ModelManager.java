package seedu.application.model;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
    private final ApplicationBook applicationBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Application> filteredApplications;
    private final ObservableList<Application> applicationsWithInterview;

    /**
     * Initializes a ModelManager with the given applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyApplicationBook applicationBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(applicationBook, userPrefs);

        logger.fine("Initializing with application book: " + applicationBook + " and user prefs " + userPrefs);

        this.applicationBook = new ApplicationBook(applicationBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredApplications = initialiseFilterList(this.applicationBook);
        applicationsWithInterview = filterApplicationsWithInterview();
    }

    public ModelManager() {
        this(new ApplicationBook(), new UserPrefs());
    }


    private static FilteredList<Application> initialiseFilterList(ApplicationBook applicationBook) {
        HideArchiveFromListPredicate hideArchiveFromListPredicate =
                new HideArchiveFromListPredicate();
        FilteredList<Application> initialList = new FilteredList<>(applicationBook.getApplicationList());
        initialList.setPredicate(hideArchiveFromListPredicate);
        return initialList;
    }

    private ObservableList<Application> filterApplicationsWithInterview() {
        ObservableList<Application> applicationsWithInterview = FXCollections.observableList(
                this.applicationBook
                        .getApplicationList()
                        .stream()
                        .filter(application -> application.getInterview().isPresent())
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
        this.applicationBook.resetData(applicationBook);
    }

    @Override
    public ReadOnlyApplicationBook getApplicationBook() {
        return applicationBook;
    }

    @Override
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return applicationBook.hasApplication(application);
    }

    @Override
    public boolean hasSameInterviewTime(Application application) {
        requireNonNull(application);
        return applicationBook.hasSameInterviewTime(application);
    }

    @Override
    public boolean hasSameInterviewTime(Interview interview) {
        requireNonNull(interview);
        return applicationBook.hasSameInterviewTime(interview);
    }

    @Override
    public boolean hasSameInterviewTimeExcludeSelf(Interview interview, Application application) {
        requireNonNull(interview);
        return applicationBook.hasSameInterviewTimeExcludeSelf(interview, application);
    }

    @Override
    public void deleteApplication(Application target) {
        applicationBook.removeApplication(target);
    }

    @Override
    public void addApplication(Application application) {
        applicationBook.addApplication(application);
        hideArchiveInFilteredApplicationList();
    }

    @Override
    public void archiveApplication(Application target) {
        applicationBook.setArchive(target);
        hideArchiveInFilteredApplicationList();
    }

    @Override
    public void retrieveApplication(Application target) {
        applicationBook.retrieveApplication(target);
        showArchiveInFilteredApplicationList();
    }

    @Override
    public void setApplication(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        applicationBook.setApplication(target, editedApplication);
    }

    //=========== Filtered Application List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Application} backed by the internal list of
     * {@code versionedApplicationBook}
     */
    @Override
    public ObservableList<Application> getFilteredApplicationList() {
        return filteredApplications;
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
    public void hideArchiveInFilteredApplicationList() {
        Predicate<Application> predicate = new HideArchiveFromListPredicate();
        filteredApplications.setPredicate(predicate);
    }

    @Override
    public void showArchiveInFilteredApplicationList() {
        Predicate<Application> predicate = new ShowArchiveOnlyPredicate();
        filteredApplications.setPredicate(predicate);
    }

    /**
     * Updates the interview list.
     */
    public void updateApplicationListWithInterview() {
        applicationsWithInterview.clear();
        applicationsWithInterview.addAll(filteredApplications);
        applicationsWithInterview.removeIf(application -> application.getInterview().isEmpty());
        applicationsWithInterview.sort(new InterviewComparator());
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
        return applicationBook.equals(other.applicationBook)
                && userPrefs.equals(other.userPrefs)
                && filteredApplications.equals(other.filteredApplications);
    }

}
