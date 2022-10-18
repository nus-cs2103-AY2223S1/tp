package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;

/**
 * Finds and displays the calorie content of the specified {@code Food} (if any).
 * Food name matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and displays the calorie content of the "
            + "specified food\n"
            + "Parameters: FOOD\n"
            + "Example: " + COMMAND_WORD + " rice";

    public static final String MESSAGE_FIND_FOOD_CALORIES_SUCCESS = "Calorie content of %1$s: %2$d kcal";
    public static final String MESSAGE_FOOD_CALORIES_NOT_FOUND = "No calorie content information for %1$s";

    private final String foodName;

    /**
     * Creates a FindCommand to find the calorie content of the given {@code Food}.
     * @param foodName The {@code Food} to find the calorie content of.
     */
    public FindCommand(String foodName) {
        this.foodName = foodName.toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Map<String, Calorie> foodCalorieList = model.getFoodCaloriesList();
        if (!foodCalorieList.containsKey(foodName)) {
            return new CommandResult(String.format(MESSAGE_FOOD_CALORIES_NOT_FOUND, foodName));
        }
        int calories = foodCalorieList.get(foodName).getCalorieValue();
        return new CommandResult(String.format(MESSAGE_FIND_FOOD_CALORIES_SUCCESS, foodName, calories));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && foodName.equals(((FindCommand) other).foodName)); // state check
    }
}
