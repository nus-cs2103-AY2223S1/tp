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
    private final PersonModel personModel;
    private final PropertyModel propertyModel;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Property> filteredProperties;

    /**
     * Initializes a ModelManager with the given personModel and userPrefs.
     */
    public ModelManager(ReadOnlyPersonModel personModel, ReadOnlyPropertyModel propertyModel,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(personModel, propertyModel, userPrefs);

        logger.fine("Initializing with person model: " + personModel + " and property model: " + propertyModel
                + " and user prefs " + userPrefs);

        this.personModel = new PersonModel(personModel);
        this.propertyModel = new PropertyModel(propertyModel);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personModel.getPersonList());
        filteredProperties = new FilteredList<>(this.propertyModel.getPropertyList());
    }

    public ModelManager() {
        this(new PersonModel(), new PropertyModel(), new UserPrefs());
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

    //=========== PersonModel ================================================================================

    @Override
    public void setPersonModel(ReadOnlyPersonModel personModel) {
        this.personModel.resetData(personModel);
    }

    @Override
    public ReadOnlyPersonModel getPersonModel() {
        return personModel;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personModel.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        personModel.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        personModel.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        personModel.setPerson(target, editedPerson);
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

    //=========== PropertyModel ================================================================================

    @Override
    public void setPropertyModel(ReadOnlyPropertyModel propertyModel) {
        this.propertyModel.resetData(propertyModel);
    }

    @Override
    public ReadOnlyPropertyModel getPropertyModel() {
        return propertyModel;
    }

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyModel.hasProperty(property);
    }

    @Override
    public void deleteProperty(Property target) {
        propertyModel.removeProperty(target);
    }

    @Override
    public void addProperty(Property property) {
        propertyModel.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);
        propertyModel.setProperty(target, editedProperty);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code PropertyModel}
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
                && personModel.equals(other.personModel)
                && propertyModel.equals(other.propertyModel)
                && filteredPersons.equals(other.filteredPersons)
                && filteredProperties.equals(other.filteredProperties);
    }

}
