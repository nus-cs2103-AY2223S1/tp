package tracko.logic.commands.order;

import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.logic.commands.CommandTestUtil.showOrderAtIndex;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListOrdersCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
        expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListOrdersCommand(), model, ListOrdersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showOrderAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListOrdersCommand(), model, ListOrdersCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
