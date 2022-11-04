package nus.climods.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.logic.commands.CommandResult;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UserModule;
import nus.climods.storage.exceptions.StorageException;

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
     * @throws StorageException If an error occurs during saving to storage.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, StorageException;

    ReadOnlyModuleList getModuleList();

    ObservableList<UserModule> getFilteredUserModuleList();

    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getUserModuleListPath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
