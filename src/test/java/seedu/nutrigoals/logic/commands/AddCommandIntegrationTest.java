package seedu.nutrigoals.logic.commands;

import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.UserPrefs;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.testutil.FoodBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());
    }

    @Test
    public void execute_newFood_success() {
        Food validFood = new FoodBuilder().build();

        Model expectedModel = new ModelManager(model.getNutriGoals(), new UserPrefs());
        expectedModel.addFood(validFood);

        assertCommandSuccess(new AddCommand(validFood), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFood), expectedModel);
    }


}
