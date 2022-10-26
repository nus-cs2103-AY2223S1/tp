package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the mass linkers data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MassLinkers massLinkers;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given massLinkers and userPrefs.
     */
    public ModelManager(ReadOnlyMassLinkers massLinkers, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(massLinkers, userPrefs);

        logger.fine("Initializing with mass linkers: " + massLinkers + " and user prefs " + userPrefs);

        this.massLinkers = new MassLinkers(massLinkers);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.massLinkers.getPersonList());
    }

    public ModelManager() {
        this(new MassLinkers(), new UserPrefs());
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
    public Path getMassLinkersFilePath() {
        return userPrefs.getMassLinkersFilePath();
    }

    @Override
    public void setMassLinkersFilePath(Path massLinkersFilePath) {
        requireNonNull(massLinkersFilePath);
        userPrefs.setMassLinkersFilePath(massLinkersFilePath);
    }

    //=========== MassLinkers ================================================================================

    @Override
    public void setMassLinkers(ReadOnlyMassLinkers massLinkers) {
        this.massLinkers.resetData(massLinkers);
    }

    @Override
    public ReadOnlyMassLinkers getMassLinkers() {
        return massLinkers;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return massLinkers.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        massLinkers.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        massLinkers.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        massLinkers.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedMassLinkers}
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
        return massLinkers.equals(other.massLinkers)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
