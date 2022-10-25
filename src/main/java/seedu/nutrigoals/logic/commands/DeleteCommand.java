package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.nutrigoals.commons.core.Messages;
import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.Food;

/**
 * Deletes a meal identified using it's displayed index from the food list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meal identified by the index number used in the displayed meal list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MEAL_SUCCESS = "Food item deleted!\n\n%1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEAL_DISPLAYED_INDEX);
        }

        Food foodToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteFood(foodToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MEAL_SUCCESS, foodToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
