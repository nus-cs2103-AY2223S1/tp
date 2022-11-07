package seedu.condonery.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.condonery.commons.core.GuiSettings;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

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
     * Returns the PropertyDirectory.
     *
     * @see seedu.condonery.model.Model#getPropertyDirectory()
     */
    ReadOnlyPropertyDirectory getPropertyDirectory();

    /** Returns an unmodifiable view of the filtered list of properties */
    ObservableList<Property> getFilteredPropertyList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getPropertyDirectoryFilePath();

    /**
     * Returns the ClientDirectory.
     *
     * @see seedu.condonery.model.Model#getClientDirectory()
     */
    ReadOnlyClientDirectory getClientDirectory();

    /** Returns an unmodifiable view of the filtered list of clients*/
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getClientDirectoryFilePath();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getUserImageDirectoryPath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
