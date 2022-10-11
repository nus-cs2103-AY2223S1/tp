package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;
import eatwhere.foodguide.model.UserPrefs;
import eatwhere.foodguide.testutil.TypicalEateries;

public class ClearCommandTest {

    @Test
    public void execute_emptyFoodGuide_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFoodGuide_success() {
        Model model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
        expectedModel.setFoodGuide(new FoodGuide());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
