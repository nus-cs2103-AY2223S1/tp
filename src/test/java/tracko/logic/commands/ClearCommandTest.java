package tracko.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.AddOrderCommand;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.TrackO;
import tracko.model.UserPrefs;
import tracko.model.order.Order;
import tracko.testutil.OrderBuilder;

public class ClearCommandTest {

    @Test
    public void execute_emptyTrackO_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        ClearCommand command = new ClearCommand();

        // Test for confirmation message displayed to user
        assertCommandSuccess(command, model, ClearCommand.CLEAR_CONFIRMATION_MESSAGE, expectedModel);

        // simulate confirmation from user
        command.setAwaitingInput(false);

        assertCommandSuccess(command, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyTrackO_cancelled() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        ClearCommand command = new ClearCommand();

        // Test for confirmation message displayed to user
        assertCommandSuccess(command, model, ClearCommand.CLEAR_CONFIRMATION_MESSAGE, expectedModel);

        // simulate cancellation from user
        command.setAwaitingInput(false);
        command.cancel();

        assertCommandSuccess(command, model, ClearCommand.MESSAGE_COMMAND_ABORTED, expectedModel);
    }

    @Test
    public void execute_nonEmptyTrackO_success() {
        Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
        Model expectedModel = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

        ClearCommand command = new ClearCommand();

        // Test for confirmation message displayed to user
        assertCommandSuccess(command, model, ClearCommand.CLEAR_CONFIRMATION_MESSAGE, expectedModel);

        // simulate confirmation from user
        command.setAwaitingInput(false);

        expectedModel.setTrackO(new TrackO());

        assertCommandSuccess(command, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTrackO_cancelled() {
        Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
        Model expectedModel = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());

        ClearCommand command = new ClearCommand();

        // Test for confirmation message displayed to user
        assertCommandSuccess(command, model, ClearCommand.CLEAR_CONFIRMATION_MESSAGE, expectedModel);

        // simulate cancellation from user
        command.setAwaitingInput(false);
        command.cancel();

        assertCommandSuccess(command, model, ClearCommand.MESSAGE_COMMAND_ABORTED, expectedModel);
    }

    @Test
    public void equals() {
        Order alice = new OrderBuilder().withName("Alice").build();
        AddOrderCommand addAliceCommand = new AddOrderCommand(alice);

        ClearCommand command = new ClearCommand();

        // same object -> returns true
        assertTrue(command.equals(command));

        // same type -> returns true
        assertTrue(command.equals(new ClearCommand()));

        // different types -> returns false
        assertFalse(command.equals(1));

        // different type -> returns false
        assertFalse(command.equals(addAliceCommand));

        // null -> returns false
        assertFalse(command.equals(null));

        // different isAwaitingInput values -> returns false
        ClearCommand otherAwaiting = new ClearCommand();
        otherAwaiting.setAwaitingInput(false);
        assertFalse(command.equals(otherAwaiting));

        // different isCancelled values -> returns false
        ClearCommand otherCancelled = new ClearCommand();
        otherCancelled.cancel();
        assertFalse(command.equals(otherCancelled));
    }

}
