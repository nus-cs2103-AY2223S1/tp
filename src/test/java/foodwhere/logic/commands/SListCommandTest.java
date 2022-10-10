package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static foodwhere.logic.commands.CommandTestUtil.showStallAtIndex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SListCommand.
 */
public class SListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new SListCommand(), model, SListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);
        assertCommandSuccess(new SListCommand(), model, SListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
