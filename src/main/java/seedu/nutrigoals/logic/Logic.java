package seedu.nutrigoals.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.logic.commands.CommandResult;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.meal.Food;

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
     * Returns the NutriGoals.
     *
     * @see seedu.nutrigoals.model.Model#getNutriGoals()
     */
    ReadOnlyNutriGoals getNutriGoals();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Returns the user prefs' nutrigoals file path.
     */
    Path getNutriGoalsFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
