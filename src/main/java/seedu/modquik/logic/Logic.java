package seedu.modquik.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.modquik.commons.core.GuiSettings;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.tutorial.Tutorial;

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
     * Returns the ModQuik.
     *
     * @see seedu.modquik.model.Model#getModQuik()
     */
    ReadOnlyModQuik getModQuik();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of reminders */
    ObservableList<Reminder> getFilteredReminderList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Tutorial> getFilteredTutorialList();

    /** Returns an unmodifiable view of the filtered list of consultations */
    ObservableList<Consultation> getFilteredConsultationList();

    /** Returns an unmodifiable view of the student's grade */
    ObservableList<PieChart.Data> getStudentGradeChartData();

    /**
     * Returns the user prefs' modquik book file path.
     */
    Path getModQuikFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
