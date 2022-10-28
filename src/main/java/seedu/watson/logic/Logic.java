package seedu.watson.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.watson.commons.core.GuiSettings;
import seedu.watson.logic.commands.CommandResult;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.Model;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.student.Student;
import seedu.watson.storage.Storage;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Hacks
     */
    Model getModel();

    /**
     * Hacks
     */
    Storage getStorage();

    /**
     * Returns the Database.
     *
     * @see seedu.watson.model.Model#getDatabase()
     */
    ReadOnlyDatabase getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Student> getFilteredPersonList();

    /**
     * Returns the user prefs' watson book file path.
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
