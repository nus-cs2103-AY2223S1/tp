package coydir.logic;

import java.nio.file.Path;

import coydir.commons.core.GuiSettings;
import coydir.logic.commands.CommandResult;
import coydir.logic.commands.exceptions.CommandException;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.ReadOnlyDatabase;
import coydir.model.person.Person;
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
     * Returns the database.
     *
     * @see coydir.model.Model#getDatabase()
     */
    ReadOnlyDatabase getDatabase();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the whole list of persons */
    ObservableList<Person> getUnfilteredPersonList();

    /**
     * Returns the user prefs' database file path.
     */
    Path getDatabaseFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
