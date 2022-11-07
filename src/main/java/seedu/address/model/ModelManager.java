package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of Survin data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedSurvin survin;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given survin and userPrefs.
     */
    public ModelManager(ReadOnlySurvin survin, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(survin, userPrefs);

        logger.fine("Initializing with Survin: " + survin + " and user prefs " + userPrefs);

        this.survin = new VersionedSurvin(survin);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.survin.getPersonList());
    }

    public ModelManager() {
        this(new Survin(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

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
    public Path getSurvinFilePath() {
        return userPrefs.getSurvinFilePath();
    }

    @Override
    public void setSurvinFilePath(Path survinFilePath) {
        requireNonNull(survinFilePath);
        userPrefs.setSurvinFilePath(survinFilePath);
    }

    // =========== Survin
    // ================================================================================

    @Override
    public void setSurvin(ReadOnlySurvin survin) {
        this.survin.resetData(survin);
    }

    @Override
    public ReadOnlySurvin getSurvin() {
        return survin;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return survin.hasPerson(person);
    }

    @Override
    public Optional<Person> getPerson(Person person) {
        requireNonNull(person);
        return survin.getPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        survin.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        survin.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        survin.setPerson(target, editedPerson);
    }

    @Override
    public void commitSurvin() {
        survin.commit();
    }

    @Override
    public void undoSurvin() {
        survin.undo();
    }

    @Override
    public boolean canUndoSurvin() {
        return survin.canUndo();
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of {@code versionedSurvin}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return survin.equals(other.survin) && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
