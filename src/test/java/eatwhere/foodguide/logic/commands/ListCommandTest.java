package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;
import eatwhere.foodguide.model.UserPrefs;
import eatwhere.foodguide.testutil.TypicalEateries;
import eatwhere.foodguide.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodGuide(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showEateryAtIndex(model, TypicalIndexes.INDEX_FIRST_EATERY);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
