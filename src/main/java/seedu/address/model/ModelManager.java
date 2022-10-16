package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.UniqueCommissionList;
import seedu.address.model.customer.Customer;
import seedu.address.ui.GuiTab;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final ObservableObject<FilteredList<Commission>> observableFilteredCommissions = new ObservableObject<>();
    private final ObservableObject<Customer> selectedCustomer = new ObservableObject<>();
    private final ObservableObject<Commission> selectedCommission = new ObservableObject<>();

    private GuiTab selectedTab = GuiTab.CUSTOMER;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredCustomers = new FilteredList<>(this.addressBook.getCustomerList());

        setSelectedCustomerCommissions(null);

        // selectedCustomerCommissions is tied to the selectedCustomer,
        // it should only be updated here.
        selectedCustomer.addListener((selectedCustomer, oldCustomer, newCustomer) -> {
            if (oldCustomer == null || !oldCustomer.isSameCustomer(newCustomer)) {
                setSelectedCustomerCommissions(newCustomer);
            }
        });


        // Temporarily set selected customer to the first customer.
        // TODO: Should be fixed by implementer of the opencus command.
        if (filteredCustomers.size() > 0) {
            selectCustomer(filteredCustomers.get(0));
        }

        // Temporarily sets selected commission to the first commission
        // TODO: Should be fixed by implementer of the opencom command
        if (observableFilteredCommissions.getValue() != null && observableFilteredCommissions.getValue().size() > 0) {
            selectCommission(observableFilteredCommissions.getValue().get(0));
        }
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    private void setSelectedCustomerCommissions(Customer customer) {
        ObservableList<Commission> commissionsList = customer == null
                ? new UniqueCommissionList().asUnmodifiableObservableList()
                : customer.getCommissionList();
        observableFilteredCommissions.setValue(new FilteredList<>(commissionsList));
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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

    //=========== ArtBuddy ================================================================================
    @Override
    public void selectTab(GuiTab tab) {
        selectedTab = tab;
    }

    public GuiTab getSelectedTab() {
        return selectedTab;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
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
        if (filteredCustomers.size() == 0) {
            selectCustomer(null);
        } else if (hasSelectedCustomer() && filteredCustomers.stream()
                .noneMatch(selectedCustomer.getValue()::isSameCustomer)) {
            selectCustomer(filteredCustomers.get(0));
        }
    }

    //=========== Filtered Commission List Accessors =============================================================

    @Override
    public FilteredList<Commission> getFilteredCommissionList() {
        return observableFilteredCommissions.getValue();
    }

    @Override
    public ObservableValue<FilteredList<Commission>> getObservableFilteredCommissionList() {
        return observableFilteredCommissions;
    }

    @Override
    public void updateFilteredCommissionList(Predicate<Commission> predicate) {
        requireAllNonNull(observableFilteredCommissions.getValue(), predicate);
        observableFilteredCommissions.getValue().setPredicate(predicate);
    }

    //=========== Selected Customer =============================================================

    @Override
    public void selectCustomer(Customer customer) {
        if (customer == null || !customer.isSameCustomer(selectedCustomer.getValue())) {
            selectedCommission.setValue(null); // resets the selected commission
        }
        selectedCustomer.setValue(customer);
    }

    @Override
    public ObservableObject<Customer> getSelectedCustomer() {
        return selectedCustomer;
    }

    /**
     * Returns whether there is a selected customer.
     */
    @Override
    public boolean hasSelectedCustomer() {
        return getSelectedCustomer().getValue() != null;
    }

    //=========== Selected Commission =============================================================

    @Override
    public void selectCommission(Commission commission) {
        selectedCommission.setValue(commission);
    }

    @Override
    public ObservableObject<Commission> getSelectedCommission() {
        return selectedCommission;
    }

    /**
     * Returns whether there is a selected commission.
     */
    @Override
    public boolean hasSelectedCommission() {
        return getSelectedCommission().getValue() != null;
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
