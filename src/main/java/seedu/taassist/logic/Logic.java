package seedu.taassist.logic;

import java.nio.file.Path;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentView;

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
     * Returns the TaAssist.
     *
     * @see seedu.taassist.model.Model#getTaAssist()
     */
    ReadOnlyTaAssist getTaAssist();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns a view of students along with its associated session data */
    ObservableList<StudentView> getStudentViewList();

    /** Returns an unmodifiable view of the list of module classes */
    ObservableList<ModuleClass> getModuleClassList();

    /** Returns an unmodifiable view of the list of sessions */
    ObservableList<Session> getSessionList();

    /**
     * Returns the user prefs' TaAssist file path.
     */
    Path getTaAssistFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the current focus label as a {@code SimpleStringProperty}.
     */
    SimpleStringProperty getFocusLabelProperty();

}
