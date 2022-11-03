package tracko.logic.commands;

import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import tracko.model.Model;
import tracko.model.ModelManager;

public class HelpCommandTest {

     private Model model = new ModelManager();
     private Model expectedModel = new ModelManager();

     @Test
     public void execute_help_success() {
         CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
         assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
     }
}
