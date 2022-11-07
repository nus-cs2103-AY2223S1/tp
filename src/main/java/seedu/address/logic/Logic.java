package seedu.address.logic;

import java.nio.file.Path;
import java.util.function.Consumer;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

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

    /** Returns an unmodifiable view of the filtered list of suppliers */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of supply items */
    ObservableList<SupplyItem> getFilteredSupplyItemList();

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
     * Increases the SupplyItem amount.
     */
    Consumer<Integer> increaseSupplyItemHandler(int index);

    /**
     * Decreases the SupplyItem amount.
     */
    Consumer<Integer> decreaseSupplyItemHandler(int index);

    /**
     * Changes the increase/decrease amount of the SupplyItem.
     */
    Consumer<Integer> changeIncDecAmountHandler(int index);
}
