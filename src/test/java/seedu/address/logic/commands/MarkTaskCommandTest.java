package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.tasks.MarkTaskCommand.ALREADY_MARKED;
import static seedu.address.logic.commands.tasks.MarkTaskCommand.MESSAGE_USAGE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tasks.MarkTaskCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TypicalTasks;

public class MarkTaskCommandTest {

    private Model model = new ModelManager(TypicalTasks.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        Task taskToMark = model.getFromFilteredTasks(INDEX_FIRST);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);
        markTaskCommand.execute(model);
        ModelManager expectedModel = new ModelManager(TypicalTasks.getTypicalAddressBook(), new UserPrefs());
        Task taskToMarkExpected = expectedModel.getFromFilteredTasks(INDEX_FIRST);
        expectedModel.setTask(taskToMarkExpected, taskToMarkExpected.mark());
        assertTrue(taskToMark.equals(taskToMarkExpected));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() throws CommandException {
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(null);
        assertThrows(CommandException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), () ->
                markTaskCommand.execute(model));
    }

    @Test
    public void execute_duplicateMarkTask_throwsCommandException() throws CommandException {
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);
        Task taskToMark = model.getFromFilteredTasks(INDEX_FIRST);
        model.setTask(taskToMark, taskToMark.mark());
        assertThrows(CommandException.class, String.format(ALREADY_MARKED, taskToMark), () ->
                markTaskCommand.execute(model));
    }
}
