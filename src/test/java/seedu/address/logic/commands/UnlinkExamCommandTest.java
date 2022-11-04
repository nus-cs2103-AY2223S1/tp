package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_LINKED_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_UNLINKED_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnlinkExamCommand}.
 */
public class UnlinkExamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToUnlink = model.getFilteredTaskList().get(INDEX_LINKED_TASK.getZeroBased());
        // ensures that the task is linked
        assertTrue(taskToUnlink.isLinked());

        UnlinkExamCommand unlinkExamCommand = new UnlinkExamCommand(INDEX_LINKED_TASK);
        String expectedMessage = String.format(unlinkExamCommand.EXAM_UNLINKED_SUCCESS, taskToUnlink);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskToUnlink).withNoExam().build();
        expectedModel.replaceTask(model.getFilteredTaskList().get(INDEX_LINKED_TASK.getZeroBased()), editedTask,
                true);

        assertCommandSuccess(unlinkExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        // index "size of list + 1" chosen as boundary value for partition [size of list + 1...INT_MAX]
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnlinkExamCommand unlinkExamCommand = new UnlinkExamCommand(outOfBoundIndex);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertCommandFailure(unlinkExamCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidTaskUnfilteredList_throwsCommandException() {
        // unlinked task -> throws error
        UnlinkExamCommand unlinkExamCommand = new UnlinkExamCommand(INDEX_UNLINKED_TASK);
        String expectedMessage = unlinkExamCommand.TASK_ALREADY_UNLINKED;

        assertCommandFailure(unlinkExamCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_LINKED_TASK);

        Task taskToUnlink = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        // ensures that the task is linked
        assertTrue(taskToUnlink.isLinked());

        UnlinkExamCommand unlinkExamCommand = new UnlinkExamCommand(INDEX_FIRST_TASK);
        String expectedMessage = String.format(UnlinkExamCommand.EXAM_UNLINKED_SUCCESS, taskToUnlink);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskToUnlink).withNoExam().build();
        expectedModel.replaceTask(model.getFilteredTaskList().get(0), editedTask, true);

        assertCommandSuccess(unlinkExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        // index "2" chosen as boundary value for partition [size of list + 1...INT_MAX]
        showTaskAtIndex(model, INDEX_LINKED_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        UnlinkExamCommand unlinkExamCommand = new UnlinkExamCommand(outOfBoundIndex);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertCommandFailure(unlinkExamCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        UnlinkExamCommand unlinkExamFirstCommand = new UnlinkExamCommand(INDEX_FIRST_TASK);
        UnlinkExamCommand unlinkExamSecondCommand = new UnlinkExamCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(unlinkExamFirstCommand.equals(unlinkExamFirstCommand));

        // same values -> returns true
        UnlinkExamCommand unlinkExamFirstCommandCopy = new UnlinkExamCommand(INDEX_FIRST_TASK);
        assertTrue(unlinkExamFirstCommand.equals(unlinkExamFirstCommandCopy));

        // different types -> returns false
        assertFalse(unlinkExamFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unlinkExamFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(unlinkExamFirstCommand.equals(unlinkExamSecondCommand));
    }
}
