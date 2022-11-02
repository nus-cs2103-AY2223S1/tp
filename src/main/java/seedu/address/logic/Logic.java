package seedu.address.logic;

import java.nio.file.Path;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyTruthTable;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

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
     * Returns the TruthTable.
     *
     * @see seedu.address.model.Model#getTruthTable()
     */
    ReadOnlyTruthTable getTruthTable();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    ObservableList<Person> getFilteredMemberList();

    ObservableList<Task> getFilteredTaskList();

    ObservableList<Team> getTeamList();

    Team getCurrentTeam();

    /**
     * Returns the user prefs' TruthTable file path.
     */
    Path getTruthTableFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ObjectProperty<Team> getTeamAsProperty();
}
