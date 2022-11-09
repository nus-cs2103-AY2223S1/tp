package seedu.nutrigoals.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_IDEAL_WEIGHT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.commons.util.StringUtil;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.testutil.EditFoodDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_APPLE_CALORIE = "50";
    public static final String VALID_APPLE_NAME = "Apple";
    public static final String VALID_BREAD_NAME = "Bread";
    public static final String VALID_BREAD_CALORIE = "150";
    public static final String VALID_SUSHI_NAME = "Sushi";
    public static final String VALID_BISCUIT_NAME = "Biscuit";
    public static final String VALID_TAG_BREAKFAST = "breakfast";
    public static final String VALID_TAG_LUNCH = "lunch";
    public static final String VALID_HEIGHT_AMOUNT = "170";
    public static final String VALID_WEIGHT_AMOUNT = "60";
    public static final String VALID_GENDER = "M";

    public static final String CALORIE_DESC_BREAD = " " + PREFIX_CALORIE + VALID_BREAD_CALORIE;
    public static final String NAME_DESC_BREAD = " " + PREFIX_NAME + VALID_BREAD_NAME;
    public static final String TAG_DESC_BREAKFAST = " " + PREFIX_TAG + VALID_TAG_BREAKFAST;
    public static final String TAG_DESC_LUNCH = " " + PREFIX_TAG + VALID_TAG_LUNCH;
    public static final String WEIGHT_VALID_WEIGHT = " " + PREFIX_WEIGHT + VALID_WEIGHT_AMOUNT;
    public static final String GENDER_VALID_GENDER = " " + PREFIX_GENDER + VALID_GENDER;
    public static final String HEIGHT_VALID_HEIGHT = " " + PREFIX_HEIGHT + VALID_HEIGHT_AMOUNT;
    public static final String IDEAL_VALID_WEIGHT = " " + PREFIX_IDEAL_WEIGHT + VALID_WEIGHT_AMOUNT;
    public static final String VALID_AGE_DESC = " " + PREFIX_AGE + "20";

    public static final String INVALID_WEIGHT_NEGATIVE = " " + PREFIX_WEIGHT + "-40"; //negative weights not allowed
    public static final String INVALID_WEIGHT_TOO_MUCH = " " + PREFIX_WEIGHT + "1000"; //number too large
    public static final String INVALID_WEIGHT_DESC = " " + PREFIX_WEIGHT + "abc"; //should only contain integers
    public static final String INVALID_IDEAL_NEGATIVE = " " + PREFIX_IDEAL_WEIGHT + "-40"; //no negative weights
    public static final String INVALID_IDEAL_TOO_MUCH = " " + PREFIX_IDEAL_WEIGHT + "1000"; //number too large
    public static final String INVALID_IDEAL_DESC = " " + PREFIX_IDEAL_WEIGHT + "abc"; //should only contain integers
    public static final String INVALID_HEIGHT_NEGATIVE = " " + PREFIX_HEIGHT + "-40"; //negative weights not allowed
    public static final String INVALID_HEIGHT_TOO_MUCH = " " + PREFIX_HEIGHT + "1000"; //number too large
    public static final String INVALID_HEIGHT_DESC = " " + PREFIX_HEIGHT + "abc"; //should only contain integers
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "man"; //should be a single char

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Sushi&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "breakfast*"; // '*' not allowed in tags

    public static final EditCommand.EditFoodDescriptor DESC_BREAKFAST = new EditFoodDescriptorBuilder()
            .withName(VALID_BREAD_NAME)
            .withCalorie("150")
            .withTags(VALID_TAG_BREAKFAST)
            .build();

    public static final EditCommand.EditFoodDescriptor DESC_LUNCH = new EditFoodDescriptorBuilder()
            .withName(VALID_SUSHI_NAME)
            .withCalorie("300")
            .withTags(VALID_TAG_LUNCH)
            .build();

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Modified assertCommandSuccess for TipCommand to work around random output of the models.
     *
     * Executes the given {@code tipCommand}, confirms that <br>
     * - the returned {@link CommandResult} partially matches {@code expectedMessage} <br>
     * - the {@code actualModel} partially matches {@code expectedModel}
     */
    public static void assertTipCommandSuccess(TipCommand tipCommand, Model actualModel, String expectedSubstring,
            Model expectedModel) {
        try {
            CommandResult actualResult = tipCommand.execute(actualModel);
            String actualSubstring = actualResult.getFeedbackToUser().substring(0, 15);
            assertEquals(actualSubstring, expectedSubstring);
            assertEquals(actualModel, expectedModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the NutriGoals, filtered food list and selected food in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        NutriGoals expectedNutriGoals = new NutriGoals(actualModel.getNutriGoals());
        List<Food> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFoodList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedNutriGoals, actualModel.getNutriGoals());
        assertEquals(expectedFilteredList, actualModel.getFilteredFoodList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the food at the given {@code targetIndex} in the
     * {@code model}'s NutriGoals.
     */
    public static void showFoodAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFoodList().size());

        Food food = model.getFilteredFoodList().get(targetIndex.getZeroBased());
        filterListByFoodName(model, food);

        assertEquals(1, model.getFilteredFoodList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only foods with the given {@code Food} name.
     */
    public static void filterListByFoodName(Model model, Food food) {
        final String[] splitName = food.getName().fullName.split("\\s+");
        List<String> keywords = Arrays.asList(splitName[0]);
        Predicate<Food> filterPredicate = foodToFilter -> keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(foodToFilter.getName().fullName, keyword));
        model.updateFilteredFoodList(filterPredicate);
    }
}
