package seedu.nutrigoals.logic.commands;

import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ReviewCommand}.
 */
public class ReviewCommandTest {
    private static final int ACTUAL_CALORIE_COUNT = 14000;
    private static final int LOWER_CALORIE_COUNT = 10000;
    private static final int HIGHER_CALORIE_COUNT = 20000;
    private static final Calorie EXACT_CALORIE_TARGET = new Calorie(String.valueOf(ACTUAL_CALORIE_COUNT));
    private static final Calorie LOWER_CALORIE_TARGET = new Calorie(String.valueOf(LOWER_CALORIE_COUNT));
    private static final Calorie HIGHER_CALORIE_TARGET = new Calorie(String.valueOf(HIGHER_CALORIE_COUNT));

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());
        expectedModel = new ModelManager(model.getNutriGoals(), new UserPrefs());
    }

    @Test
    public void execute_calorieDeficiency_success() {
        ReviewCommand reviewCommand = new ReviewCommand();
        String expectedMessage = String.format(ReviewCommand.MESSAGE_REVIEW_DETAILS, ACTUAL_CALORIE_COUNT,
                HIGHER_CALORIE_COUNT, String.format(ReviewCommand.MESSAGE_CALORIE_DEFICIENCY,
                        HIGHER_CALORIE_COUNT - ACTUAL_CALORIE_COUNT));

        model.setCalorieTarget(HIGHER_CALORIE_TARGET);
        expectedModel.setCalorieTarget(HIGHER_CALORIE_TARGET);
        assertCommandSuccess(reviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_calorieExcess_success() {
        ReviewCommand reviewCommand = new ReviewCommand();
        String expectedMessage = String.format(ReviewCommand.MESSAGE_REVIEW_DETAILS, ACTUAL_CALORIE_COUNT,
                LOWER_CALORIE_COUNT, String.format(ReviewCommand.MESSAGE_CALORIE_EXCESS,
                        ACTUAL_CALORIE_COUNT - LOWER_CALORIE_COUNT));

        model.setCalorieTarget(LOWER_CALORIE_TARGET);
        expectedModel.setCalorieTarget(LOWER_CALORIE_TARGET);
        assertCommandSuccess(reviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_calorieSufficiency_success() {
        ReviewCommand reviewCommand = new ReviewCommand();
        String expectedMessage = String.format(ReviewCommand.MESSAGE_REVIEW_DETAILS, ACTUAL_CALORIE_COUNT,
                ACTUAL_CALORIE_COUNT, ReviewCommand.MESSAGE_CALORIE_SUFFICIENCY);

        model.setCalorieTarget(EXACT_CALORIE_TARGET);
        expectedModel.setCalorieTarget(EXACT_CALORIE_TARGET);
        assertCommandSuccess(reviewCommand, model, expectedMessage, expectedModel);
    }
}
