package jeryl.fyp.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.logic.commands.CommandResult;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.ReadOnlyAddressBook;
import jeryl.fyp.model.student.Student;

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
     * @see jeryl.fyp.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredPersonList();

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
