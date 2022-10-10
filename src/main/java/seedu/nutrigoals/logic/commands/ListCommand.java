package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.IsFoodAddedTodayPredicate;

/**
 * Lists all foods in nutrigoals to the user sorted by time.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all foods today";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFoodList(new IsFoodAddedTodayPredicate());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
