package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.customer.Customer;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;

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
     * Returns an unmodifiable view of the filtered customer list
     */
    ObservableList<Customer> getFilteredCustomerList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<Customer> predicate);

    /**
     * Returns the currently selected customer or null if no customer is currently selected.
     */
    ObservableObject<Customer> getSelectedCustomer();
}
