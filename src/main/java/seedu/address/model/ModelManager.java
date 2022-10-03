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
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.UniqueCommissionList;
import seedu.address.model.customer.Customer;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final FilteredList<Commission> filteredCommissions;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredCustomers = new FilteredList<>(this.addressBook.getCustomerList());

        // Temporarily set active customer to the first customer.
        // TODO: Should be fixed by implementer of the opencus command.
        if (this.addressBook.getCustomerList().size() > 0) {
            this.addressBook.setActiveCustomer(this.addressBook.getCustomerList().get(0));
            filteredCommissions = new FilteredList<>(this.addressBook.getCommissionList());
        } else {
            // TODO: figure out how to fix filteredCommissions
            filteredCommissions = new FilteredList<>(new UniqueCommissionList().asUnmodifiableObservableList());
        }
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
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer target) {
        addressBook.removeCustomer(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setCustomer(target, editedCustomer);
    }
    
    /**
     * Clones active person - useful to force GUI update
     */
    @Override
    public void updateActiveCustomer() {
        Customer activeCustomer = addressBook.getActiveCustomer();
        Customer activeCustomerClone = activeCustomer.getClone();
        setCustomer(activeCustomer, activeCustomerClone);
        addressBook.setActiveCustomer(activeCustomerClone);
    }

    @Override
    public boolean hasCommission(Commission commission) {
        requireNonNull(commission);
        return addressBook.hasCommission(commission);
    }

    @Override
    public void deleteCommission(Commission target) {
        addressBook.removeCommission(target);
    }

    @Override
    public void addCommission(Commission commission) {
        addressBook.addCommission(commission);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setCommission(Commission target, Commission editedCommission) {
        requireAllNonNull(target, editedCommission);

        addressBook.setCommission(target, editedCommission);
    }
    
    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    //=========== Filtered Commission List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Commission} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Commission> getFilteredCommissionList() {
        return filteredCommissions;
    }

    @Override
    public void updateFilteredCommissionList(Predicate<Commission> predicate) {
        requireNonNull(predicate);
        filteredCommissions.setPredicate(predicate);
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
