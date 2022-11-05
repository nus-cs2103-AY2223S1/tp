package tracko.logic.commands.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tracko.commons.core.Messages;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;
import tracko.model.order.OrderDateTimeComparator;

class SortOrdersCommandTest {

    private Model model;
    private Model sortedNewModel;
    private Model sortedOldModel;

    private OrderDateTimeComparator newComparator = new OrderDateTimeComparator("new");
    private OrderDateTimeComparator oldComparator = new OrderDateTimeComparator("old");

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

        sortedNewModel = new ModelManager(model.getTrackO(), new UserPrefs());
        sortedNewModel.updateSortedOrderList(newComparator);

        sortedOldModel = new ModelManager(model.getTrackO(), new UserPrefs());
        sortedOldModel.updateSortedOrderList(oldComparator);
    }

    @Test
    public void execute_sortedNewList_success() {
        SortOrdersCommand command = new SortOrdersCommand(newComparator);
        String expectedMessage = String.format(Messages.MESSAGE_ORDERS_SORTED_OVERVIEW,
                model.getSortedOrderList().size());
        assertCommandSuccess(command, model, expectedMessage, sortedNewModel);
        assertEquals(model.getSortedOrderList(), sortedNewModel.getSortedOrderList());
    }

    @Test
    public void execute_sortedOldList_success() {
        SortOrdersCommand command = new SortOrdersCommand(oldComparator);
        String expectedMessage = String.format(Messages.MESSAGE_ORDERS_SORTED_OVERVIEW,
                model.getSortedOrderList().size());
        assertCommandSuccess(command, model, expectedMessage, sortedOldModel);
        assertEquals(model.getSortedOrderList(), sortedOldModel.getSortedOrderList());
    }

    @Test
    public void equals() {
        SortOrdersCommand sortFirstCommand = new SortOrdersCommand(newComparator);
        SortOrdersCommand sortSecondCommand = new SortOrdersCommand(oldComparator);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortOrdersCommand sortFirstCommandCopy = new SortOrdersCommand(newComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }
}
