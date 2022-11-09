package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.Food;

/**
 * Adds a Meal to NutriGoals.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a food item to NutriGoals. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CALORIE + "CALORIES "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Banana "
            + PREFIX_CALORIE + "50 "
            + PREFIX_TAG + "breakfast";

    public static final String MESSAGE_SUCCESS = "New food item added!\n\n%1$s";
    public static final String MESSAGE_ADDED_CALORIE_TOO_LARGE = "You have consumed too many calories today! "
            + "Food item not added!\n\n%1$s";

    private final Food toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Food food) {
        requireNonNull(food);
        toAdd = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isAddedTotalCalorieTooLarge(toAdd)) {
            throw new CommandException(String.format(MESSAGE_ADDED_CALORIE_TOO_LARGE, toAdd));
        }

        model.addFood(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
