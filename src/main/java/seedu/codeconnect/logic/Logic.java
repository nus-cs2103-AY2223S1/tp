package seedu.codeconnect.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.codeconnect.commons.core.GuiSettings;
import seedu.codeconnect.logic.commands.CommandResult;
import seedu.codeconnect.logic.commands.exceptions.CommandException;
import seedu.codeconnect.logic.parser.exceptions.ParseException;
import seedu.codeconnect.model.ReadOnlyAddressBook;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.task.Task;

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
     * @see seedu.codeconnect.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' task list file path.
     */
    Path getTaskListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /** Returns an unmodifiable view of the sorted list of tasks */
    ObservableList<Task> getSortedTaskList();
}
