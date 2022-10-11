package nus.climods.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.logic.commands.CommandResult;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.Model;
import nus.climods.model.ReadOnlyAddressBook;
import nus.climods.model.module.DummyModule;
import nus.climods.model.module.UserModule;
import nus.climods.model.person.Person;

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

    ObservableList<UserModule> getFilteredModuleList() throws ParseException;

    ObservableList<UserModule> getFilteredSavedModuleList() throws ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see Model#getAddressBook()
     */
    @Deprecated
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    @Deprecated
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    @Deprecated
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
