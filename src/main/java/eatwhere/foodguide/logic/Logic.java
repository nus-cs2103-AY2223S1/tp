package eatwhere.foodguide.logic;

import java.nio.file.Path;

import eatwhere.foodguide.commons.core.GuiSettings;
import eatwhere.foodguide.logic.commands.CommandResult;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.model.eatery.Eatery;
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
     * Returns the FoodGuide.
     *
     * @see Model#getFoodGuide()
     */
    ReadOnlyFoodGuide getFoodGuide();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Eatery> getFilteredEateryList();

    /**
     * Returns the user prefs' food guide file path.
     */
    Path getFoodGuideFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
