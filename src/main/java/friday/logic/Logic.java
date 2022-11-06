package friday.logic;

import java.nio.file.Path;

import friday.commons.core.GuiSettings;
import friday.logic.commands.CommandResult;
import friday.logic.commands.exceptions.CommandException;
import friday.logic.parser.exceptions.ParseException;
import friday.model.Model;
import friday.model.ReadOnlyFriday;
import friday.model.student.Student;
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
     * Returns Friday.
     *
     * @see Model#getFriday()
     */
    ReadOnlyFriday getFriday();

    /** Returns an unmodifiable view of the filtered or sorted list of students */
    ObservableList<Student> getStudentList();

    /**
     * Returns the user prefs' Friday file path.
     */
    Path getFridayFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
