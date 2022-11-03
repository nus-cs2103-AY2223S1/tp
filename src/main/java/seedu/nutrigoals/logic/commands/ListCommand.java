package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;

/**
 * Lists all foods saved in NutriGoals, sorted by meal type and time added.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all the foods added on a specific date.\n"
            + "Parameters: [DATE (must be valid and in this format: yyyy-MM-dd)]\n"
            + "Example: " + COMMAND_WORD + " 2022-10-11";

    public static final String MESSAGE_SUCCESS = "Listed all foods on %s";
    public static final String MESSAGE_EMPTY_FOOD_LIST = "No food recorded on %s";

    private final IsFoodAddedOnThisDatePredicate predicate;

    /**
     * Creates a ListCommand to list all food items on a particular day.
     * @param predicate The predicate containing the date to filter the list of foods for.
     */
    public ListCommand(IsFoodAddedOnThisDatePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFoodList(predicate);
        if (model.isFilteredFoodListEmpty()) {
            return new CommandResult(String.format(MESSAGE_EMPTY_FOOD_LIST, predicate.getDate()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, predicate.getDate()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && predicate.equals(((ListCommand) other).predicate)); // state check
    }
}
