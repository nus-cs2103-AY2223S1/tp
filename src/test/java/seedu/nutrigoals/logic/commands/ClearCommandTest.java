package seedu.nutrigoals.logic.commands;

import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyNutriGoals_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyNutriGoals_success() {
        Model model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalNutriGoals(), new UserPrefs());
        expectedModel.setNutriGoals(new NutriGoals());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
