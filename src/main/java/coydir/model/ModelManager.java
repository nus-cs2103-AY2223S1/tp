package coydir.model;

import static coydir.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import coydir.commons.core.GuiSettings;
import coydir.commons.core.LogsCenter;
import coydir.model.person.Person;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the database data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Database database;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given database and userPrefs.
     */
    public ModelManager(ReadOnlyDatabase database, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(database, userPrefs);

        logger.fine("Initializing with database: " + database + " and user prefs " + userPrefs);

        this.database = new Database(database);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.database.getPersonList());
    }

    public ModelManager() {
        this(new Database(), new UserPrefs());
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
    public Path getDatabaseFilePath() {
        return userPrefs.getDatabaseFilePath();
    }

    @Override
    public void setDatabaseFilePath(Path databaseFilePath) {
        requireNonNull(databaseFilePath);
        userPrefs.setDatabaseFilePath(databaseFilePath);
    }

    //=========== Database ================================================================================

    @Override
    public void setDatabase(ReadOnlyDatabase database) {
        this.database.resetData(database);
    }

    @Override
    public ReadOnlyDatabase getDatabase() {
        return database;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return database.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        database.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        database.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        database.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedDatabase}
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
        return database.equals(other.database)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
