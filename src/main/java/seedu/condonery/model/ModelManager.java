package seedu.condonery.model;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PropertyDirectory propertyDirectory;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given propertyDirectory and userPrefs.
     */
    public ModelManager(ReadOnlyPropertyDirectory propertyDirectory, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(propertyDirectory, userPrefs);

        logger.fine("Initializing with address book: " + propertyDirectory + " and user prefs " + userPrefs);

        this.propertyDirectory = new PropertyDirectory(propertyDirectory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.propertyDirectory.getPersonList());
    }

    public ModelManager() {
        this(new PropertyDirectory(), new UserPrefs());
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
    public Path getPropertyDirectoryFilePath() {
        return userPrefs.getPropertyDirectoryFilePath();
    }

    @Override
    public void setPropertyDirectoryFilePath(Path propertyDirectoryFilePath) {
        requireNonNull(propertyDirectoryFilePath);
        userPrefs.setPropertyDirectoryFilePath(propertyDirectoryFilePath);
    }

    //=========== PropertyDirectory ================================================================================

    @Override
    public void setPropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory) {
        this.propertyDirectory.resetData(propertyDirectory);
    }

    @Override
    public ReadOnlyPropertyDirectory getPropertyDirectory() {
        return propertyDirectory;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return propertyDirectory.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        propertyDirectory.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        propertyDirectory.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        propertyDirectory.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedPropertyDirectory}
     */
    @Override
    public ObservableList<Person> getFilteredPropertyList() {
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
        return propertyDirectory.equals(other.propertyDirectory)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
