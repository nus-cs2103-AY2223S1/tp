package seedu.realtime.logic.commands;

import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.logic.commands.CommandTestUtil.showListingAtIndex;
import static seedu.realtime.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.realtime.testutil.TypicalListings.getTypicalRealTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ViewListingsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRealTime(), new UserPrefs());
        expectedModel = new ModelManager(model.getRealTime(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewListingsCommand(), model, ViewListingsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showListingAtIndex(model, FIRST_INDEX);
        assertCommandSuccess(new ViewListingsCommand(), model, ViewListingsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
