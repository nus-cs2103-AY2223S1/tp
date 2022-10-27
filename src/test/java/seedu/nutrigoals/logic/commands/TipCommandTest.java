package seedu.nutrigoals.logic.commands;

import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertTipCommandSuccess;
import static seedu.nutrigoals.logic.commands.TipCommand.TIP_GENERATED_SUCCESS;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.UserPrefs;

public class TipCommandTest {
    private Model model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalNutriGoals(), new UserPrefs());

    @Test
    public void execute_tip_success() {
        TipCommand testTip = new TipCommand();
        String expectedMessage = String.format(TIP_GENERATED_SUCCESS, testTip.toString()).substring(0, 15);
        assertTipCommandSuccess(testTip, model, expectedMessage, expectedModel);
    }
}
