package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
import static seedu.boba.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;

/**
 * Contains integration tests (interaction with the BobaBotModel) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private BobaBotModel bobaBotModel;
    private BobaBotModel expectedBobaBotModel;

    @BeforeEach
    public void setUp() {
        bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        expectedBobaBotModel = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), bobaBotModel, ListCommand.MESSAGE_SUCCESS, expectedBobaBotModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(bobaBotModel, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), bobaBotModel, ListCommand.MESSAGE_SUCCESS, expectedBobaBotModel);
    }
}
