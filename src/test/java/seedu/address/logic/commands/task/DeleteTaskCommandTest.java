package seedu.address.logic.commands.task;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskPanelBuilder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

public class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null));
    }

    @Test
    public void execute_validTaskIndexAccepted() {
        Index targetIndex = Index.fromOneBased(1);
        Task taskToDelete = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(targetIndex);
        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS, taskToDelete);
        ModelManager expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskPanel(model.getTaskPanel()), new UserPrefs());;
        expectedModel.deleteTask(taskToDelete);
        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }


}