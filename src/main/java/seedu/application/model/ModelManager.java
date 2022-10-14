package seedu.application.model;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.application.Application;

/**
 * Represents the in-memory model of the application book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ApplicationBook applicationBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Application> filteredApplications;

    /**
     * Initializes a ModelManager with the given applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyApplicationBook applicationBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(applicationBook, userPrefs);

        logger.fine("Initializing with application book: " + applicationBook + " and user prefs " + userPrefs);

        this.applicationBook = new ApplicationBook(applicationBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplications = new FilteredList<>(this.applicationBook.getApplicationList());
    }

    public ModelManager() {
        this(new ApplicationBook(), new UserPrefs());
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
    public void deleteApplication(Application target) {
        applicationBook.removeApplication(target);
    }

    @Override
    public void addApplication(Application application) {
        applicationBook.addApplication(application);
        updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
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
    public void updateFilteredApplicationList(Predicate<Application> predicate) {
        requireNonNull(predicate);
        filteredApplications.setPredicate(predicate);
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
