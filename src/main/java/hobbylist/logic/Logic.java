package hobbylist.logic;

import java.nio.file.Path;

import hobbylist.commons.core.AliasSettings;
import hobbylist.commons.core.GuiSettings;
import hobbylist.commons.core.ThemeSettings;
import hobbylist.logic.commands.CommandResult;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.Model;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.activity.Activity;
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
     * Returns the HobbyList.
     *
     * @see Model#getHobbyList()
     */
    ReadOnlyHobbyList getHobbyList();

    /** Returns an unmodifiable view of the filtered list of activities */
    ObservableList<Activity> getFilteredActivityList();

    /**
     * Returns an unmodifiable view of the current selected activity
     */
    ObservableList<Activity> getSelectedActivity();

    /**
     * Returns the user prefs' HobbyList file path.
     */
    Path getHobbyListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' theme settings.
     */
    ThemeSettings getThemeSettings();

    /**
     * Set the user prefs' theme settings.
     */
    void setThemeSettings(ThemeSettings themeSettings);

    /**
     * Returns the user prefs' Alias settings.
     */
    AliasSettings getAliasSettings();

    /**
     * Set the user prefs' Alias settings.
     */
    void setAliasSettings(AliasSettings aliasSettings);
}
