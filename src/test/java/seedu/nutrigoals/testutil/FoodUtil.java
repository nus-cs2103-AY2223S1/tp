package seedu.nutrigoals.testutil;

import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.nutrigoals.logic.commands.AddCommand;
import seedu.nutrigoals.logic.commands.EditCommand;
import seedu.nutrigoals.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.nutrigoals.model.meal.Food;

/**
 * A utility class for Food.
 */
public class FoodUtil {

    /**
     * Returns an add command string for adding the {@code Food}.
     */
    public static String getAddCommand(Food food) {
        return AddCommand.COMMAND_WORD + " " + getFoodDetails(food);
    }

    /**
     * Returns the part of command string for the given {@code Food}'s details.
     */
    public static String getFoodDetails(Food food) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + food.getName().fullName + " ");
        sb.append(PREFIX_CALORIE + food.getCalorie().value + " ");
        sb.append(PREFIX_TAG + food.getTag().tagName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFoodDescriptor}'s details.
     */
    public static String getEditFoodDescriptorDetails(EditFoodDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getCalorie().ifPresent(calorie -> sb.append(EditCommand.PREFIX_CALORIE)
                .append(calorie.value).append(" "));
        descriptor.getTag().ifPresent(tag -> sb.append(PREFIX_TAG).append(tag.tagName).append(" "));
        return sb.toString();
    }
}
