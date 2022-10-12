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
import seedu.address.model.property.Property;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final PersonBook personBook;
    private final ProportyBook proportyBook;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Property> filteredProperties;

    /**
     * Initializes a ModelManager with the given personBook and userPrefs.
     */
    public ModelManager(ReadOnlyPersonBook personModel, ReadOnlyPropertyBook propertyModel,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(personModel, propertyModel, userPrefs);

        logger.fine("Initializing with person model: " + personModel + " and property model: " + propertyModel
                + " and user prefs " + userPrefs);

        this.personBook = new PersonBook(personModel);
        this.proportyBook = new ProportyBook(propertyModel);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personBook.getPersonList());
        filteredProperties = new FilteredList<>(this.proportyBook.getPropertyList());
    }

    public ModelManager() {
        this(new PersonBook(), new ProportyBook(), new UserPrefs());
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
    public Path getPersonModelFilePath() {
        return userPrefs.getPersonBookFilePath();
    }

    @Override
    public void setPersonModelFilePath(Path personModelFilePath) {
        requireNonNull(personModelFilePath);
        userPrefs.setPersonBookFilePath(personModelFilePath);
    }

    @Override
    public Path getPropertyModelFilePath() {
        return userPrefs.getPropertyBookFilePath();
    }

    @Override
    public void setPropertyModelFilePath(Path propertyModelFilePath) {
        requireNonNull(propertyModelFilePath);
        userPrefs.setPropertyBookFilePath(propertyModelFilePath);
    }

    //=========== PersonBook ================================================================================

    @Override
    public void setPersonModel(ReadOnlyPersonBook personModel) {
        this.personBook.resetData(personModel);
    }

    @Override
    public ReadOnlyPersonBook getPersonModel() {
        return personBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        personBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        personBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        personBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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

    //=========== ProportyBook ================================================================================

    @Override
    public void setPropertyModel(ReadOnlyPropertyBook propertyModel) {
        this.proportyBook.resetData(propertyModel);
    }

    @Override
    public ReadOnlyPropertyBook getPropertyModel() {
        return proportyBook;
    }

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return proportyBook.hasProperty(property);
    }

    @Override
    public void deleteProperty(Property target) {
        proportyBook.removeProperty(target);
    }

    @Override
    public void addProperty(Property property) {
        proportyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);
        proportyBook.setProperty(target, editedProperty);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code ProportyBook}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
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
        return userPrefs.equals(other.userPrefs)
                && personBook.equals(other.personBook)
                && proportyBook.equals(other.proportyBook)
                && filteredPersons.equals(other.filteredPersons)
                && filteredProperties.equals(other.filteredProperties);
    }

}
