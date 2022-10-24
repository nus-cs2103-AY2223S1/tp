package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;

/**
 * Provides a summary of the user's daily calorie intake, target calorie and the calorie deficiency or excess.
 */
public class ReviewCommand extends Command {
    public static final String COMMAND_WORD = "review";

    public static final String MESSAGE_REVIEW_DETAILS = "Your calorie intake for today: %1$s\n"
            + "Your calorie target for today: %2$s\n"
            + "%3$s";

    public static final String MESSAGE_CALORIE_DEFICIENCY = "You should consume %1$d more calories to reach your "
            + "calorie target for today!";
    public static final String MESSAGE_CALORIE_EXCESS = "You have consumed %1$d more calories than your calorie "
            + "target for today!";
    public static final String MESSAGE_CALORIE_SUFFICIENCY = "You have reached your calorie target for today!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        DateTime today = new DateTime();
        model.updateFilteredFoodList(new IsFoodAddedOnThisDatePredicate(today));
        Calorie calorieCount = model.getTotalCalorie();
        Calorie calorieTarget = model.getCalorieTarget();
        int difference = model.getCalorieDifference();

        String calorieStatus;
        if (difference > 0) {
            calorieStatus = String.format(MESSAGE_CALORIE_DEFICIENCY, difference);
        } else if (difference < 0) {
            calorieStatus = String.format(MESSAGE_CALORIE_EXCESS, -difference);
        } else {
            calorieStatus = MESSAGE_CALORIE_SUFFICIENCY;
        }

        return new CommandResult(String.format(MESSAGE_REVIEW_DETAILS, calorieCount, calorieTarget, calorieStatus));
    }
}
