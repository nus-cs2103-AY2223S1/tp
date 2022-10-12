package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.IsFoodAddedTodayPredicate;

/**
 * Provides a summary of the user's daily calorie intake, target calorie and the calorie deficiency or excess.
 */
public class ReviewCommand extends Command {
    public static final String COMMAND_WORD = "review";

    public static final String MESSAGE_REVIEW_DETAILS = "Your calorie intake for today: %1$d\n"
            + "Your calorie target for today: %2$d\n"
            + "%3$s";

    public static final String MESSAGE_CALORIE_DEFICIENCY = "You should consume %1$d more calorie(s) to reach your "
            + "calorie target for today!";
    public static final String MESSAGE_CALORIE_EXCESS = "You have consumed %1$d more calorie(s) than your calorie "
            + "target for today!";
    public static final String MESSAGE_CALORIE_SUFFICIENCY = "You have reached your calorie target for today!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFoodList(new IsFoodAddedTodayPredicate());
        List<Food> listOfFoodsToday = model.getFilteredFoodList();

        int calorieCount = 0;
        for (Food food : listOfFoodsToday) {
            calorieCount += food.getCalorie().getCalorieValue();
        }
        int calorieTarget = model.getCalorieTarget().getCalorieValue();
        int calorieDifference = calorieTarget - calorieCount;

        String calorieStatus;
        if (calorieDifference > 0) {
            calorieStatus = String.format(MESSAGE_CALORIE_DEFICIENCY, calorieDifference);
        } else if (calorieDifference < 0) {
            calorieStatus = String.format(MESSAGE_CALORIE_EXCESS, -calorieDifference);
        } else {
            calorieStatus = MESSAGE_CALORIE_SUFFICIENCY;
        }

        return new CommandResult(String.format(MESSAGE_REVIEW_DETAILS, calorieCount, calorieTarget, calorieStatus));
    }
}
