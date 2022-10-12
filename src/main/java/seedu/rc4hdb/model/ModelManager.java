package seedu.rc4hdb.model;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.model.resident.Resident;

/**
 * Represents the in-memory model of the resident book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ResidentBook residentBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Resident> filteredResidents;
    private final ObservableList<String> observableFieldList;

    /**
     * Initializes a ModelManager with the given residentBook and userPrefs.
     */
    public ModelManager(ReadOnlyResidentBook residentBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(residentBook, userPrefs);

        logger.fine("Initializing with resident book: " + residentBook + " and user prefs " + userPrefs);
        this.residentBook = new ResidentBook(residentBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredResidents = new FilteredList<>(this.residentBook.getResidentList());
        this.observableFieldList = FXCollections.observableArrayList();
    }

    public ModelManager() {
        this(new ResidentBook(), new UserPrefs());
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
    public Path getResidentBookFilePath() {
        return userPrefs.getResidentBookFilePath();
    }

    @Override
    public void setResidentBookFilePath(Path residentBookFilePath) {
        requireNonNull(residentBookFilePath);
        userPrefs.setResidentBookFilePath(residentBookFilePath);
    }

    //=========== ResidentBook ===============================================================================

    public void setResidentBook(ReadOnlyResidentBook residentBook) {
        this.residentBook.resetData(residentBook);
    }

    @Override
    public ReadOnlyResidentBook getResidentBook() {
        return residentBook;
    }

    @Override
    public boolean hasResident(Resident person) {
        requireNonNull(person);
        return residentBook.hasResident(person);
    }

    @Override
    public void deleteResident(Resident target) {
        residentBook.removeResident(target);
    }

    @Override
    public void addResident(Resident person) {
        residentBook.addResident(person);
        updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
    }

    @Override
    public void setResident(Resident target, Resident editedResident) {
        requireAllNonNull(target, editedResident);

        residentBook.setResident(target, editedResident);
    }

    //=========== Filtered Resident List Accessors ===========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Resident} backed by the internal list of
     * {@code versionedResidentBook}
     */
    @Override
    public ObservableList<Resident> getFilteredResidentList() {
        return filteredResidents;
    }

    @Override
    public void updateFilteredResidentList(Predicate<Resident> predicate) {
        requireNonNull(predicate);
        filteredResidents.setPredicate(predicate);
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
        return residentBook.equals(other.residentBook)
                && userPrefs.equals(other.userPrefs)
                && filteredResidents.equals(other.filteredResidents);
    }

    //=========== Observable Field List Accessors =============================================================

    @Override
    public ObservableList<String> getObservableFields() {
        return this.observableFieldList;
    }

    @Override
    public void setObservableFields(List<String> modifiableFields) {
        this.observableFieldList.setAll(modifiableFields);
    }
}
