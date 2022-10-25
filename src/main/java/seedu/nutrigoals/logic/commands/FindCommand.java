package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import javafx.collections.transformation.FilteredList;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodInPredicate;
import seedu.nutrigoals.model.meal.Name;

/**
 * Finds and displays the calorie content of the specified food (if any).
 * Food name matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and displays the calorie content of the "
            + "specified food\n"
            + "Parameters: FOOD\n"
            + "Example: " + COMMAND_WORD + " rice";

    public static final String MESSAGE_FIND_FOOD_CALORIE_SUCCESS = "Calorie content of %1$s: %2$s";
    public static final String MESSAGE_FOOD_CALORIES_NOT_FOUND = "No calorie content information for %1$s";

    private final Name foodName;
    private FilteredList<Food> filteredList;
    private final IsFoodInPredicate predicate;

    /**
     * Creates a FindCommand to find the calorie content of the given food.
     * @param foodName The {@code Name} of the food to find the calorie content of.
     */
    public FindCommand(Name foodName) {
        this.foodName = foodName;
        predicate = new IsFoodInPredicate(foodName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Map<Name, Calorie> foodCalorieList = model.getFoodCalorieList();
        filteredList = new FilteredList<>(model.getUnFilteredFoodList());
        filteredList.setPredicate(predicate);

        // if food is in filtered list return average of calories in list
        if (filteredList.size() != 0) {
            Integer sum = 0;
            for (Food food : filteredList) {
                sum += food.getCalorie().getCalorieValue();
            }
            sum /= filteredList.size();
            Calorie average = new Calorie(sum.toString());
            return new CommandResult(String.format(MESSAGE_FIND_FOOD_CALORIE_SUCCESS, foodName, average));
        }

        if (!foodCalorieList.containsKey(foodName)) {
            throw new CommandException(String.format(MESSAGE_FOOD_CALORIES_NOT_FOUND, foodName));
        }

        Calorie calorie = foodCalorieList.get(foodName);
        return new CommandResult(String.format(MESSAGE_FIND_FOOD_CALORIE_SUCCESS, foodName, calorie));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && foodName.equals(((FindCommand) other).foodName)); // state check
    }
}
