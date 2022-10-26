package seedu.foodrem.logic.commands.itemcommands;

import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "Listed all items";

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodRem(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, EXPECTED_SUCCESS_MESSAGE, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        assertCommandSuccess(new ListCommand(), model, EXPECTED_SUCCESS_MESSAGE, expectedModel);
    }
}
