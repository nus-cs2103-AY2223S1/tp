package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyProfNus;
import seedu.address.model.module.Module;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

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
     * Returns the ProfNus.
     *
     * @see seedu.address.model.Model#getProfNus()
     */
    ReadOnlyProfNus getProfNus();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the whole list of persons */
    ObservableList<Person> getAllPersonList();

    /** Returns an unmodifiable view of the filtered list of tutors */
    ObservableList<Student> getFilteredTutorList();

    /** Returns an unmodifiable view of the whole list of tutors */
    ObservableList<Student> getAllTutorList();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<Module> getFilteredModuleList();

    /** Returns an unmodifiable view of the whole list of modules */
    ObservableList<Module> getAllModuleList();


    /** Returns an unmodifiable view of the filtered list of module schedules */
    ObservableList<Schedule> getFilteredScheduleList();

    /** Returns an unmodifiable view of the whole list of module schedules */
    ObservableList<Schedule> getAllScheduleList();

    /**
     * Returns the user prefs' profNus file path.
     */
    Path getProfNusFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


}
