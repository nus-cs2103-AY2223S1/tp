package seedu.studmap.logic;

import java.io.File;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.studmap.commons.core.GuiSettings;
import seedu.studmap.logic.commands.CommandResult;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.logic.imports.exceptions.ImportException;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.student.Student;

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
     * Returns the StudMap.
     *
     * @see seedu.studmap.model.Model#getStudMap()
     */
    ReadOnlyStudMap getStudMap();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' student map file path.
     */
    Path getStudMapFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    String importFile(File file) throws ImportException;
}
