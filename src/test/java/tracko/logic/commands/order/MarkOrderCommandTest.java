package tracko.logic.commands.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST;
import static tracko.testutil.TypicalIndexes.INDEX_SECOND;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.Test;

import tracko.commons.core.Messages;
import tracko.commons.core.index.Index;
import tracko.logic.commands.CommandTestUtil;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteOrderCommand}.
 */
public class MarkOrderCommandTest {

    private Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getOrderList().size() + 1);
        MarkOrderCommand markOrderCommand = new MarkOrderCommand(outOfBoundIndex, true, false);

        CommandTestUtil.assertCommandFailure(markOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showOrderAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of order list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrackO().getOrderList().size());

        MarkOrderCommand markOrderCommand = new MarkOrderCommand(outOfBoundIndex, true, false);

        CommandTestUtil.assertCommandFailure(markOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkOrderCommand markFirstCommand = new MarkOrderCommand(INDEX_FIRST, true, false);
        MarkOrderCommand markSecondCommand = new MarkOrderCommand(INDEX_SECOND, true, false);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkOrderCommand markFirstCommandCopy = new MarkOrderCommand(INDEX_FIRST, true, false);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }
}
