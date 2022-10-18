package seedu.nutrigoals.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private static final String FOOD_RICE = "rice";
    private static final String FOOD_ONION = "onion";
    private static final int CALORIES_RICE = 260;
    private Model model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalNutriGoals(), new UserPrefs());

    @Test
    public void equals() {
        FindCommand findFirstCommand = new FindCommand(FOOD_RICE);
        FindCommand findSecondCommand = new FindCommand(FOOD_ONION);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(FOOD_RICE);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different food -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_foodWithCalorieContent_calorieContentFound() {
        FindCommand findCommand = new FindCommand(FOOD_RICE);
        String expectedMessage =
                String.format(FindCommand.MESSAGE_FIND_FOOD_CALORIES_SUCCESS, FOOD_RICE, CALORIES_RICE);
        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_foodWithNoCalorieContent_noCalorieContentFound() {
        FindCommand findCommand = new FindCommand(FOOD_ONION);
        String expectedMessage = String.format(FindCommand.MESSAGE_FOOD_CALORIES_NOT_FOUND, FOOD_ONION);
        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void constructor_caseInsensitiveFoodName_success() {
        FindCommand expectedFindCommand = new FindCommand("rice");

        assertEquals(expectedFindCommand, new FindCommand("rIcE"));
        assertEquals(expectedFindCommand, new FindCommand("RICE"));
        assertEquals(expectedFindCommand, new FindCommand("Rice"));
    }
}
