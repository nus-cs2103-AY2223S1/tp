package seedu.classify.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.classify.commons.core.GuiSettings;
import seedu.classify.logic.commands.CommandResult;
import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.FilteredStudents;
import seedu.classify.model.Model;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.student.Student;


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
     * Returns the StudentRecord.
     *
     * @see Model#getStudentRecord()
     */
    ReadOnlyStudentRecord getStudentRecord();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' student record file path.
     */
    Path getStudentRecordFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    FilteredStudents getFilteredStudents();

}
