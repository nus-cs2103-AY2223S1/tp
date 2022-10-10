package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReminderCommand.SHOWING_REMINDER_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ReminderCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_reminder_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_REMINDER_MESSAGE, true);
        assertCommandSuccess(new ReminderCommand(), model, expectedCommandResult, expectedModel);
    }
}
