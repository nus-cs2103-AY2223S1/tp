package seedu.foodrem.logic.commands.generalcommands;

import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.testutil.TypicalFoodRem;

class ResetCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "FoodRem has been reset!";
    private final Model model = new ModelManager(TypicalFoodRem.getTypicalFoodRem(), new UserPrefs());

    @Test
    void execute() {
        Model expectedModel = model;
        expectedModel.setFoodRem(new FoodRem());
        assertCommandSuccess(new ResetCommand(), model, EXPECTED_SUCCESS_MESSAGE, expectedModel);
    }
}
