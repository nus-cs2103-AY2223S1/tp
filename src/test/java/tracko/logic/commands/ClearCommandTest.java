package tracko.logic.commands;

import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import org.junit.jupiter.api.Test;

import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.TrackO;
import tracko.model.UserPrefs;

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

}
