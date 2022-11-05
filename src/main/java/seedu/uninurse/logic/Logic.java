package seedu.uninurse.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.logic.commands.CommandResult;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.Schedule;
import seedu.uninurse.model.person.Patient;


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
     * Returns the UninurseBook.
     *
     * @see seedu.uninurse.model.Model#getUninurseBook()
     */
    ReadOnlyUninurseBook getUninurseBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Patient> getFilteredPersonList();

    /**
     * Returns the user prefs' uninurse book file path.
     */
    Path getUninurseBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the patient of interest.
     */
    Patient getPatientOfInterest();

    /**
     * Returns the schedule.
     */
    Schedule getSchedule();

    /**
     * Returns the saved PatientListTracker.
     */
    public PersonListTracker getSavedPatientListTracker();
}
