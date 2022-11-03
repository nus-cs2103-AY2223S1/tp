package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_PRIORITY_TAG_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Set;

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
 * Integration testing for DeleteTagCommandTest together with some unit testing conducted
 */
public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Set<String> priorityKeyword = Set.of("priority");
    private final Set<String> deadlineKeyword = Set.of("deadline");
    private final Set<String> priorityAndDeadlineKeyword = Set.of("priority", "deadline");

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, priorityKeyword));
    }

    @Test
    public void constructor_nullKeywordSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(INDEX_FIRST_TASK,
                null));
    }

    @Test
    public void execute_validIndexValidKeywordUnFilteredList_success() {
        Task task = model.getFilteredTaskList().get(INDEX_PRIORITY_TAG_TASK.getZeroBased());
        //Checks that task contains priority keyword
        assertTrue(task.hasPriorityTag());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_PRIORITY_TAG_TASK, priorityKeyword);
        String expectedSuccessMessage = DeleteTagCommand.TAG_DELETED_SUCCESSFULLY;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithDeletedPriorityTag = new TaskBuilder(task).withPriorityTag(null).build();
        expectedModel.replaceTask(task, taskWithDeletedPriorityTag, true);

        assertCommandSuccess(deleteTagCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexValidKeywordUnfilteredList_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getAddressBook().getTaskList().size() + 1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundsIndex, priorityKeyword);
        String expectedFailureMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(deleteTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_noPriorityTagPresentUnfilteredList_throwsCommandException() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        //Checks that first task has no priority tag
        assertFalse(firstTask.hasPriorityTag());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_TASK, priorityKeyword);
        String expectedFailureMessage = DeleteTagCommand.NO_PRIORITY_TAG_TO_DELETE;
        assertCommandFailure(deleteTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_noDeadlineTagPresentUnfilteredList_throwsCommandException() {
        Task secondTask = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        //Checks tat second task has no deadline tag
        assertFalse(secondTask.hasDeadlineTag());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_SECOND_TASK, deadlineKeyword);
        String expectedFailureMessage = DeleteTagCommand.NO_DEADLINE_TAG_TO_DELETE;
        assertCommandFailure(deleteTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_validIndexValidKeywordFilteredList_success() {
        showTaskAtIndex(model, INDEX_PRIORITY_TAG_TASK);
        Task priorityTask = model.getFilteredTaskList().get(0);
        //checks whether priority task has priority tag
        assertTrue(priorityTask.hasPriorityTag());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_TASK, priorityKeyword);
        String expectedSuccessMessage = DeleteTagCommand.TAG_DELETED_SUCCESSFULLY;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithDeletedPriorityTag = new TaskBuilder(priorityTask).withPriorityTag(null).build();
        expectedModel.replaceTask(priorityTask, taskWithDeletedPriorityTag, true);

        assertCommandSuccess(deleteTagCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexValidKeywordFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_PRIORITY_TAG_TASK);
        Index outOfBoundsIndex = INDEX_PRIORITY_TAG_TASK;
        //Checks whether outOfBounds index is still within range of task list in address book
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getAddressBook().getTaskList().size());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundsIndex, priorityAndDeadlineKeyword);
        String expectedFailureMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(deleteTagCommand, model, expectedFailureMessage);
    }

}
