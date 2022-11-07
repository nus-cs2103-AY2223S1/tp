package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.ui.GuiTab;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Commission> PREDICATE_SHOW_ALL_COMMISSIONS = unused -> true;

    /**
     * {@code Comparator} that sorts by name
     */
    Comparator<Customer> CUSTOMER_NAME_COMPARATOR = Comparator.comparing(
            customer -> customer.getName().fullName.toLowerCase());

    /**
     * {@code Comparator} that sorts by number of commissions
     */
    Comparator<Customer> CUSTOMER_NUM_COMMISSIONS_COMPARATOR = Comparator.comparing(Customer::getCommissionsCount);

    /**
     * {@code Comparator} that sorts by number of active commissions
     */
    Comparator<Customer> CUSTOMER_NUM_ACTIVE_COMMISSIONS_COMPARATOR =
            Comparator.comparing(Customer::getActiveCommissionCount);

    /**
     * {@code Comparator} that sorts by customer's revenue
     */
    Comparator<Customer> CUSTOMER_REVENUE_COMPARATOR = Comparator.comparing(Customer::getRevenue);

    /**
     * {@code Comparator} that sorts by the customer's latest commission
     */
    Comparator<Customer> CUSTOMER_LAST_DATE_COMPARATOR = Comparator.comparing(
            customer -> Optional.ofNullable(customer.getLastDate()).orElse(LocalDate.MIN));

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    boolean hasCustomer(Customer customer);

    /**
     * Deletes the given customer.
     * The customer must exist in the address book.
     */
    void deleteCustomer(Customer target);

    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the address book.
     */
    void addCustomer(Customer customer);

    /**
     * Replaces the given customer {@code target} with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer
     * in the address book.
     */
    void setCustomer(Customer target, Customer editedCustomer);

    /** Specifies the given customer as the selected customer. */
    void selectCustomer(Customer customer);

    /**
     * Returns an unmodifiable view of the sorted and filtered customer list
     */
    ObservableList<Customer> getSortedFilteredCustomerList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredCustomerList(Predicate<Customer> predicate);

    /**
     * Updates the comparator of the sorted customer list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedCustomerList(Comparator<Customer> comparator);



    /**
     * Adds commission to specified customer.
     */
    void addCommission(Customer customer, Commission commission);

    /**
     * Replaces the given commission {@code target} in the customer's commission list with {@code editedCommission}.
     * {@code target} must exist in the address book.
     * The commission identity of {@code editedCommission} must not be the same as another existing commission in the
     * customer's commission list.
     */
    void setCommission(Customer customer, Commission target, Commission editedCommission);


    /**
     * Removes {@code key} from this {@code Customer}.
     * {@code key} must exist in the customer's commission list.
     */
    void removeCommission(Customer customer, Commission key);

    /**
     * Returns an unmodifiable view of the list of {@code Commission} backed by the internal list of
     * {@code versionedAddressBook}
     */
    FilteredList<Commission> getFilteredCommissionList();

    /**
     * Returns an observable instance of the current filtered list of {@code Commission}
     */
    ObservableObject<Pair<Customer, FilteredList<Commission>>> getObservableFilteredCommissionList();

    /**
     * Updates the filter of the current filtered commission list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCommissionList(Predicate<Commission> predicate);

    /**
     * Returns the aggregate sum of fees from all the commissions in the @code observableFilteredCommissions
     */
    Double getTotalRevenue();

    boolean hasSelectedCustomer();

    /**
     * Returns ObservableObject containing the currently selected customer or null if no customer is currently selected.
     */
    ObservableObject<Customer> getSelectedCustomer();

    /** Specifies a commission as the selected commission. */
    void selectCommission(Commission commission);

    /**
     * Returns ObservableObject containing the currently selected commission
     * or an empty ObservableObject if no commission is currently selected.
     */
    ObservableObject<Commission> getSelectedCommission();

    /** Returns whether any commission is currently being selected. **/
    boolean hasSelectedCommission();


    /** Sets the selected tab as the given tab. */
    void selectTab(GuiTab tab);

    /**
     * Returns the selected tab.
     */
    GuiTab getSelectedTab();

    boolean isSameCustomerAsSelectedCustomer(Customer customer);
}
