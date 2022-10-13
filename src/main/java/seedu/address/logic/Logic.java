package seedu.address.logic;

import java.nio.file.Path;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ObservableObject;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of customers */
    ObservableList<Customer> getFilteredCustomerList();

    /** Returns an unmodifiable view of the filtered list of commission */
    ObservableValue<FilteredList<Commission>> getObservableFilteredCommissionList();
    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the currently selected customer.
     */
    ObservableObject<Customer> getSelectedCustomer();

    void selectCustomer(Customer customer);

    /**
     * Returns the currently selected commission.
     */
    ObservableObject<Commission> getSelectedCommission();

    void selectCommission(Commission commission);

    /** Selects a customer within the existing list */
    void selectValidCustomer();

    /** Selects a commission within the existing list */
    void selectValidCommission();
}
