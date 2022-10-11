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
import seedu.address.model.role.Buyer;
import seedu.address.model.role.Seller;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PersonModel personModel;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given personModel and userPrefs.
     */
    public ModelManager(ReadOnlyPersonModel addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.personModel = new PersonModel(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personModel.getPersonList());
    }

    public ModelManager() {
        this(new PersonModel(), new UserPrefs());
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
        return userPrefs.getPersonModelFilePath();
    }

    @Override
    public void setPersonModelFilePath(Path personModelFilePath) {
        requireNonNull(personModelFilePath);
        userPrefs.setPersonModelFilePath(personModelFilePath);
    }

    @Override
    public Path getPropertyModelFilePath() {
        return userPrefs.getPropertyModelFilePath();
    }

    @Override
    public void setPropertyModelFilePath(Path propertyModelFilePath) {
        requireNonNull(propertyModelFilePath);
        userPrefs.setPropertyModelFilePath(propertyModelFilePath);
    }

    //=========== PersonModel ================================================================================

    @Override
    public void setAddressBook(ReadOnlyPersonModel addressBook) {
        this.personModel.resetData(addressBook);
    }

    @Override
    public ReadOnlyPersonModel getAddressBook() {
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

    @Override
    public void setBuyerRole(Person person, Buyer buyer) {
        requireAllNonNull(person, buyer);
        personModel.setBuyerRole(person, buyer);
    }

    @Override
    public void setSellerRole(Person person, Seller seller) {
        requireAllNonNull(person, seller);
        person.setSeller(seller);
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
        return personModel.equals(other.personModel)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
