package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_DEADLINE_TAG_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_LAST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_PRIORITY_TAG_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.exceptions.BothTagsCannotBeNullException;
import seedu.address.model.task.Task;
import seedu.address.testutil.DeadlineTagBuilder;
import seedu.address.testutil.PriorityTagBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Integration test for AddTagCommandTest together with some unit testing
 */
public class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        assertThrows(NullPointerException.class, () -> new AddTagCommand(priorityTag, null, null));
    }

    @Test
    public void constructor_bothTagsNull_throwsBothTagsCannotBeNullException() {
        assertThrows(BothTagsCannotBeNullException.class, () -> new AddTagCommand(null, null,
                INDEX_FIRST_TASK));
    }

    @Test
    public void execute_validIndexWithValidPriorityTagUnfilteredList_success() {
        //Choose lower bound of partition for [1..size of list]
        Task task = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        assertFalse(task.hasPriorityTag());
        AddTagCommand addTagCommand = new AddTagCommand(priorityTag, null, INDEX_FIRST_TASK);
        String expectedSuccessMessage = AddTagCommand.TAG_ADDED_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithPriorityTag = new TaskBuilder(task).withPriorityTag(priorityTag).build();
        expectedModel.replaceTask(task, taskWithPriorityTag, true);

        assertCommandSuccess(addTagCommand, model, expectedSuccessMessage, expectedModel);

    }

    @Test
    public void execute_validIndexUpperBoundWithValidDeadlineTagUnfilteredList_success() {
        //Choose upper bound of partition for [1..size of list]
        Task task = model.getFilteredTaskList().get(INDEX_LAST_TASK.getZeroBased());
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        assertFalse(task.hasDeadlineTag());
        AddTagCommand addTagCommand = new AddTagCommand(null, deadlineTag, INDEX_LAST_TASK);
        String expectedSuccessMessage = AddTagCommand.TAG_ADDED_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithDeadlineTag = new TaskBuilder(task).withDeadlineTag(deadlineTag).build();
        expectedModel.replaceTask(task, taskWithDeadlineTag, true);

        assertCommandSuccess(addTagCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexWithValidPriorityTagUnfilteredList_throwsCommandException() {
        //Choose lower bound of partition [size of list + 1...INT_MAX]
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        AddTagCommand addTagCommand = new AddTagCommand(priorityTag, null, outOfBoundsIndex);
        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_priorityTagAlreadyExists_throwsCommandException() {
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        AddTagCommand addTagCommand = new AddTagCommand(priorityTag, null, INDEX_PRIORITY_TAG_TASK);
        String expectedMessage = AddTagCommand.PRIORITY_TAG_ALREADY_EXIST;
        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_deadlineTagAlreadyExists_throwsCommandException() {
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        AddTagCommand addTagCommand = new AddTagCommand(null, deadlineTag, INDEX_DEADLINE_TAG_TASK);
        String expectedMessage = AddTagCommand.DEADLINE_TAG_ALREADY_EXIST;
        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexWithValidDeadlineTagFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Task task = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        //Ensures that task does not have deadline tag before adding it
        assertFalse(task.hasDeadlineTag());
        AddTagCommand addTagCommand = new AddTagCommand(null, deadlineTag, INDEX_FIRST_TASK);
        String expectedSuccessMessage = AddTagCommand.TAG_ADDED_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithDeadlineTag = new TaskBuilder(task).withDeadlineTag(deadlineTag).build();
        expectedModel.replaceTask(task, taskWithDeadlineTag, true);
        assertCommandSuccess(addTagCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexWithValidPriorityTagFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        //Since model's filtered list only has 1 task, index "2" is out of bounds
        Index outOfBoundsIndex = INDEX_SECOND_TASK;
        //Ensures that out of bounds index is still within range of address book task list
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getAddressBook().getTaskList().size());
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        AddTagCommand addTagCommand = new AddTagCommand(priorityTag, null, outOfBoundsIndex);
        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

}

