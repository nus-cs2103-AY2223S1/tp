package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Phone;
import seedu.address.model.customer.Reward;
import seedu.address.model.customer.exceptions.PersonNotFoundException;
import seedu.address.model.exceptions.NextStateNotFoundException;
import seedu.address.model.exceptions.PreviousStateNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);
        LocalDate currentDate = LocalDate.now();
        String currentMonth = String.valueOf(currentDate.getMonth().getValue());

        for (Customer customer : addressBook.getPersonList()) {
            if (customer.getBirthdayMonth().value.equals(currentMonth)) {
                customer.addBirthdayTag();
            } else {
                customer.removeBirthdayTag();
            }
        }
        this.addressBook = new AddressBook(addressBook);
        this.versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasPerson(customer);
    }

    @Override
    public void deletePerson(Customer target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Customer customer) {
        addressBook.addPerson(customer);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setPerson(target, editedCustomer);
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit(this.addressBook);
    }

    @Override
    public void undoAddressBook() throws PreviousStateNotFoundException {
        versionedAddressBook.undo(this.addressBook);
    }

    @Override
    public void redoAddressBook() throws NextStateNotFoundException {
        versionedAddressBook.redo(this.addressBook);
    }

    /**
     * Returns the index of the customer with the same phone number.
     *
     * @param phone Phone number to search
     * @return index of the customer with the same phone number
     * @throws PersonNotFoundException if no customer with corresponding phone number found
     */
    @Override
    public int findNum(Phone phone) throws PersonNotFoundException {
        requireNonNull(phone);
        return addressBook.findNum(phone);
    }

    /**
     * Returns the index of the customer with the same email.
     *
     * @param email Email to search
     * @return index of the customer with the same email
     * @throws PersonNotFoundException if no customer with corresponding email found
     */
    @Override
    public int findEmail(Email email) throws PersonNotFoundException {
        requireNonNull(email);
        return addressBook.findEmail(email);
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param phone Phone number of the Customer of interest
     * @return the current Reward points of a Customer
     */
    @Override
    public Reward getCurrentReward(Phone phone) {
        requireAllNonNull(phone);
        return addressBook.getCurrentReward(phone);
    }

    /**
     * Returns the current Reward points of a Customer
     *
     * @param email Email of the Customer of interest
     * @return the current Reward points of a Customer
     */
    @Override
    public Reward getCurrentReward(Email email) {
        requireAllNonNull(email);
        return addressBook.getCurrentReward(email);
    }

    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredPersonList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredCustomers.equals(other.filteredCustomers);
    }
}
