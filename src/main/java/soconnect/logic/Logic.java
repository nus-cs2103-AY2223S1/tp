package soconnect.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import soconnect.commons.core.GuiSettings;
import soconnect.logic.autocomplete.Autocomplete;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.Model;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.person.Person;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return The result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the SoConnect.
     *
     * @see Model#getSoConnect()
     */
    ReadOnlySoConnect getSoConnect();

    /**
     * Returns an unmodifiable view of the filtered list of persons.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' SoConnect file path.
     */
    Path getSoConnectFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' attribute order in GUI settings.
     */
    String getAttributeOrder();

    /**
     * Returns the user prefs' hidden attributes in GUI settings.
     */
    String getHiddenAttributes();

    /**
     * Returns the AutocompleteManager.
     */
    Autocomplete getAutocompleteManager();
}
