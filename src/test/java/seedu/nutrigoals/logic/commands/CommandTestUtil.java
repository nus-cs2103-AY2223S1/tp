package seedu.nutrigoals.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.NameContainsKeywordsPredicate;
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
    public static final String VALID_BISCUIT_CALORIE = "200";
    public static final String VALID_TAG_BREAKFAST = "breakfast";
    public static final String VALID_TAG_LUNCH = "lunch";

    public static final String CALORIE_DESC_APPLE = " " + PREFIX_CALORIE + VALID_APPLE_CALORIE;
    public static final String CALORIE_DESC_BREAD = " " + PREFIX_CALORIE + VALID_BREAD_CALORIE;
    public static final String NAME_DESC_APPLE = " " + PREFIX_NAME + VALID_APPLE_NAME;
    public static final String NAME_DESC_BREAD = " " + PREFIX_NAME + VALID_BREAD_NAME;
    public static final String TAG_DESC_BREAKFAST = " " + PREFIX_TAG + VALID_TAG_BREAKFAST;
    public static final String TAG_DESC_LUNCH = " " + PREFIX_TAG + VALID_TAG_LUNCH;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Sushi&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "breakfast*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
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
        final String[] splitName = food.getName().fullName.split("\\s+");
        model.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFoodList().size());
    }

}
