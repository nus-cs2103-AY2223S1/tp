package tracko.logic.commands.item;

import static tracko.testutil.TypicalIndexes.INDEX_FIRST;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tracko.logic.commands.CommandTestUtil;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;

public class ListItemsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
        expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(
                new ListItemsCommand(), model, ListItemsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showOrderAtIndex(model, INDEX_FIRST);
        CommandTestUtil.assertCommandSuccess(
                new ListItemsCommand(), model, ListItemsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
