package seedu.foodrem.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.item.Item;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException         If an error occurs during command execution.
     * @throws IllegalArgumentException If an error occurs during parsing.
     */
    CommandResult<?> execute(String commandText) throws CommandException, IllegalArgumentException;

    /**
     * Returns the FoodRem.
     *
     * @see seedu.foodrem.model.Model#getFoodRem()
     */
    ReadOnlyFoodRem getFoodRem();

    /**
     * Returns an unmodifiable view of the current list of items.
     */
    ObservableList<Item> getCurrentList();

    /**
     * Returns the user prefs' foodRem file path.
     */
    Path getFoodRemFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
