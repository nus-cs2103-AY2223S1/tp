package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return The result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Executes the given command and returns the result.
     * @param command The given command.
     * @return The result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult executeGivenCommand(Command command) throws CommandException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of objects */
    ObservableList<Object> getFilteredCurrList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Converts the buyer's orders from a {@code List} to a {@code ObservableList} and returns the result.
     * @return An {@code ObservableList} instance containing all the buyer's orders.
     */
    ObservableList<Order> getOrderAsObservableListFromBuyer(Buyer buyer);
    /**
     * Converts the delverer's orders from a {@code List} to a {@code ObservableList} and returns the result.
     * @return An {@code ObservableList} instance containing all the deliverer's orders.
     */
    ObservableList<Order> getOrderAsObservableListFromDeliverer(Deliverer deliverer);
    /**
     * Converts the supplier's pets from a {@code List} to a {@code ObservableList} and returns the result.
     * @return An {@code ObservableList} instance containing all the supplier's pets.
     */
    ObservableList<Pet> getPetAsObservableListFromSupplier(Supplier supplier);

    /**
     * Switches to buyer list.
     */
    void switchToBuyer();

    /**
     * Switches to supplier list.
     */
    void switchToSupplier();
}
