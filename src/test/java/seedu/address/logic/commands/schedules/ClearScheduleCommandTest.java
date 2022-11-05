package seedu.address.logic.commands.schedules;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.schedule.ClearScheduleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ClearScheduleCommandTest {

    @Test
    public void execute_emptyProfNUS_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult = new CommandResult(

        )

        assertCommandSuccess(new ClearScheduleCommand(), model,
                ClearScheduleCommand.MESSAGE_CLEAR_ALL_SCHEDULES_SUCCESS, expectedModel);




    }


}
