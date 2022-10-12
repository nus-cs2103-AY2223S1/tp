package longtimenosee.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import longtimenosee.commons.core.GuiSettings;
import longtimenosee.logic.commands.CommandResult;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.ReadOnlyAddressBook;
import longtimenosee.model.client.Client;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.Policy;

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
     * @see longtimenosee.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();


    /** Returns an unmodifiable view of the filtered list of policies */
    ObservableList<Policy> getFilteredPolicyList();

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

}
