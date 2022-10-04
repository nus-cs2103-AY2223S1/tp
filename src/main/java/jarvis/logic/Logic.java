package jarvis.logic;

import java.nio.file.Path;

import jarvis.commons.core.GuiSettings;
import jarvis.logic.commands.CommandResult;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.student.Student;
import javafx.collections.ObservableList;

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
     * @see jarvis.model.Model#getStudentBook()
     */
    ReadOnlyStudentBook getStudentBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
