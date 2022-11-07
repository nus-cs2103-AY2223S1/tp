package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static foodwhere.logic.commands.CommandTestUtil.showReviewAtIndex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RListCommand.
 */
public class RListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new RListCommand(), model, RListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showReviewAtIndex(model, TypicalIndexes.INDEX_FIRST_REVIEW);
        assertCommandSuccess(new RListCommand(), model, RListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
