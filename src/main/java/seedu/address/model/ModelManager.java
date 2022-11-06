package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
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
    private final SortedList<Customer> sortedFilteredCustomers;
    private final FilteredList<Customer> filteredCustomers;
    private final ObservableObject<Pair<Customer, ObservableList<Commission>>> observableUniqueCommissions =
            new ObservableObject<>(new Pair<>(null, new UniqueCommissionList().asUnmodifiableObservableList()));
    private final ObservableObject<Pair<Customer, FilteredList<Commission>>> observableFilteredCommissions =
            new ObservableObject<>(new Pair<>(null,
                    new FilteredList<>(observableUniqueCommissions.getValue().getValue())));
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
        sortedFilteredCustomers = new SortedList<>(filteredCustomers);
        sortedFilteredCustomers.setComparator(CUSTOMER_NAME_COMPARATOR);

        setSelectedCustomerCommissions(null);

        // Makes observableFilteredCommissions reflect to the current observableUniqueCommissions list.
        observableUniqueCommissions.addListener((observableUniqueCommissions, oldList, newList) ->
                observableFilteredCommissions.setValue(new Pair<>(
                        newList.getKey(),
                        new FilteredList<>(newList.getValue()))));


        // selectedCustomerCommissions is tied to the selectedCustomer,
        // it should only be updated here.
        selectedCustomer.addListener((selectedCustomer, oldCustomer, newCustomer) ->
                setSelectedCustomerCommissions(newCustomer));

        // Sets selected customer to the first customer in filtered customer list.
        if (filteredCustomers.size() > 0) {
            selectCustomer(filteredCustomers.get(0));
        }

        // Sets selected commission to the first commission in filtered commission list.
        List<Commission> commissionList = getFilteredCommissionList();
        if (commissionList != null && commissionList.size() > 0) {
            selectCommission(commissionList.get(0));
        }
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    private void setSelectedCustomerCommissions(Customer customer) {
        Pair<Customer, ObservableList<Commission>> commissionsList = customer == null
                ? new Pair<>(null, addressBook.getFullCommissionList())
                : new Pair<>(customer, customer.getCommissionList());
        observableUniqueCommissions.setValue(commissionsList);
        selectCommission(null);
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
        selectedCustomer.setValue(null);
        selectedCommission.setValue(null);
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
     * Adds commission to specified customer.
     *
     * @param commission new commission to add.
     */
    public void addCommission(Customer customer, Commission commission) {
        requireAllNonNull(customer, commission);
        addressBook.addCommission(customer, commission);
    }

    /**
     * Replaces the given commission {@code target} in the customer's commission list with {@code editedCommission}.
     * {@code target} must exist in the address book.
     * The commission identity of {@code editedCommission} must not be the same as another existing commission in the
     * customer's commission list.
     */
    public void setCommission(Customer customer, Commission target, Commission editedCommission) {
        addressBook.setCommission(customer, target, editedCommission);
    }


    /**
     * Removes {@code key} from this {@code Customer}.
     * {@code key} must exist in the customer's commission list.
     */
    public void removeCommission(Customer customer, Commission key) {
        addressBook.removeCommission(customer, key);
    }



    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getSortedFilteredCustomerList() {
        return sortedFilteredCustomers;
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

    @Override
    public void updateSortedCustomerList(Comparator<Customer> comparator) {
        requireNonNull(comparator);
        sortedFilteredCustomers.setComparator(comparator);
    }

    //=========== Filtered Commission List Accessors =============================================================
    @Override
    public FilteredList<Commission> getFilteredCommissionList() {
        if (observableFilteredCommissions.getValue() == null) {
            return null;
        }
        return observableFilteredCommissions.getValue().getValue();
    }

    @Override
    public ObservableObject<Pair<Customer, FilteredList<Commission>>> getObservableFilteredCommissionList() {
        return observableFilteredCommissions;
    }

    @Override
    public void updateFilteredCommissionList(Predicate<Commission> predicate) {
        requireAllNonNull(getFilteredCommissionList(), predicate);
        getFilteredCommissionList().setPredicate(predicate);
    }

    //=========== Filtered Commission List Statistic Aggregator ==================================================
    @Override
    public Double getTotalRevenue() {
        double revenue = 0;
        for (Commission commission : observableFilteredCommissions.getValue().getValue()) {
            revenue += commission.getFee().fee;
        }
        return revenue;
    }

    //=========== Selected Customer =============================================================

    @Override
    public void selectCustomer(Customer customer) {
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
