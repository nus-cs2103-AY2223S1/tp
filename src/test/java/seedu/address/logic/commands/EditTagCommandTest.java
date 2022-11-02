package seedu.address.logic.commands;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_DEADLINE_TAG_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_PRIORITY_TAG_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
 * Integration test for EditTagCommandTest together with some unit testing
 */
public class EditTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        assertThrows(NullPointerException.class, () -> new EditTagCommand(null, null, deadlineTag));
    }

    @Test
    public void constructor_bothTagsNull_throwsBothTagsCannotBeNullException() {
        assertThrows(BothTagsCannotBeNullException.class, () -> new EditTagCommand(INDEX_FIRST_TASK,
                null, null));
    }

    @Test
    public void execute_validIndexWithPriorityTagUnfilteredList_success() {
        Task task = model.getFilteredTaskList().get(INDEX_PRIORITY_TAG_TASK.getZeroBased());
        PriorityTag priorityTag = new PriorityTagBuilder(getDefaultEditedPriority()).build();
        //Verifies that task must have priority tag
        assertTrue(task.hasPriorityTag());
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_PRIORITY_TAG_TASK, priorityTag, null);
        String expectedSuccessMessage = EditTagCommand.TAG_EDITED_SUCCESSFULLY;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithEditedPriorityTag = new TaskBuilder(task).withPriorityTag(priorityTag).build();
        expectedModel.replaceTask(task, taskWithEditedPriorityTag, true);

        assertCommandSuccess(editTagCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_validIndexWithDeadlineTagUnfilteredList_success() {
        Task task = model.getFilteredTaskList().get(INDEX_DEADLINE_TAG_TASK.getZeroBased());
        DeadlineTag deadlineTag = new DeadlineTagBuilder(getDefaultEditedDeadline()).build();
        //Verifies that task has deadline tag
        assertTrue(task.hasDeadlineTag());
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_DEADLINE_TAG_TASK, null, deadlineTag);
        String expectedSuccessMessage = EditTagCommand.TAG_EDITED_SUCCESSFULLY;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithEditedDeadlineTag = new TaskBuilder(task).withDeadlineTag(deadlineTag).build();
        expectedModel.replaceTask(task, taskWithEditedDeadlineTag, true);

        assertCommandSuccess(editTagCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexWithPriorityTagUnfilteredList_success() {
        //Choose Lower bound of partition for [size of list + 1...MAX_INT]
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        EditTagCommand editTagCommand = new EditTagCommand(outOfBoundsIndex, priorityTag, null);
        String expectedFailureMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(editTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_invalidIndexWithDeadlineTagUnfilteredList_success() {
        //Choose upper bound of partition for [size of list + 1...MAX_INT]
        Index outOfBoundsIndex = Index.fromOneBased(MAX_VALUE);
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        EditTagCommand editTagCommand = new EditTagCommand(outOfBoundsIndex, null, deadlineTag);
        String expectedFailureMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(editTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_noPriorityTagPresent_throwsCommandException() {
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        Task task = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        //Checks that task does not have priority tag
        assertFalse(task.hasPriorityTag());
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_FIRST_TASK, priorityTag, null);
        String expectedFailureMessage = EditTagCommand.PRIORITY_TAG_DOES_NOT_EXIST;
        assertCommandFailure(editTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_noDeadlineTagPresent_throwsCommandException() {
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        Task task = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        //Checks that task does not have deadline tag
        assertFalse(task.hasDeadlineTag());
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_FIRST_TASK, null, deadlineTag);
        String expectedFailureMessage = EditTagCommand.DEADLINE_TAG_DOES_NOT_EXIST;
        assertCommandFailure(editTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_samePriorityTagUsed_throwsCommandException() {
        PriorityTag priorityTag = new PriorityTagBuilder().build();
        Task task = model.getFilteredTaskList().get(INDEX_PRIORITY_TAG_TASK.getZeroBased());
        //Checks that task has priority tag
        assertTrue(task.hasPriorityTag());
        //Checks that priority tag in task is same as priority tag created in this method
        assertEquals(0, task.getPriorityTag().compareTo(priorityTag));
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_PRIORITY_TAG_TASK, priorityTag, null);
        String expectedFailureMessage = EditTagCommand.PRIORITY_TAG_UNCHANGED;
        assertCommandFailure(editTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_sameDeadlineTagUsed_throwsCommandException() {
        DeadlineTag deadlineTag = new DeadlineTagBuilder().build();
        Task task = model.getFilteredTaskList().get(INDEX_DEADLINE_TAG_TASK.getZeroBased());
        //Checks that task has deadline tag
        assertTrue(task.hasDeadlineTag());
        //Checks that deadline tag in task is same as deadline tag created in this method
        assertEquals(0, task.getDeadlineTag().compareTo(deadlineTag));
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_DEADLINE_TAG_TASK, null, deadlineTag);
        String expectedFailureMessage = EditTagCommand.DEADLINE_TAG_UNCHANGED;
        assertCommandFailure(editTagCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_validIndexWithPriorityTagFilteredList_success() {
        showTaskAtIndex(model, INDEX_PRIORITY_TAG_TASK);
        Task task = model.getFilteredTaskList().get(0);
        PriorityTag priorityTag = new PriorityTagBuilder(getDefaultEditedPriority()).build();
        //Checks that task has priority tag
        assertTrue(task.hasPriorityTag());
        //Checks that task's priority tag is different from priority tag created in method
        assertNotEquals(0, task.getPriorityTag().compareTo(priorityTag));
        EditTagCommand editTagCommand = new EditTagCommand(INDEX_FIRST_TASK, priorityTag, null);
        String expectedSuccessMessage = EditTagCommand.TAG_EDITED_SUCCESSFULLY;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task taskWithEditedPriorityTag = new TaskBuilder(task).withPriorityTag(priorityTag).build();
        expectedModel.replaceTask(task, taskWithEditedPriorityTag, true);
        assertCommandSuccess(editTagCommand, model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexWithDeadlineTagFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_DEADLINE_TAG_TASK);
        Task task = model.getFilteredTaskList().get(0);
        DeadlineTag deadlineTag = new DeadlineTagBuilder(getDefaultEditedDeadline()).build();
        //Checks that task has deadline tag
        assertTrue(task.hasDeadlineTag());
        //Checks that task's deadline tag is different from deadline tag created in method
        assertNotEquals(0, task.getDeadlineTag().compareTo(deadlineTag));
        Index outOfBoundsIndex = INDEX_SECOND_TASK;
        //Ensures outOfBoundsIndex still within range of address book
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getAddressBook().getTaskList().size());
        EditTagCommand editTagCommand = new EditTagCommand(outOfBoundsIndex, null, deadlineTag);
        String expectedFailureMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(editTagCommand, model, expectedFailureMessage);
    }

    private DeadlineTag getDefaultEditedDeadline() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        final LocalDate deadline = LocalDate.parse("12-12-2022", dateTimeFormatter);
        return new DeadlineTagBuilder().withDeadline(deadline).build();
    }

    private PriorityTag getDefaultEditedPriority() {
        return new PriorityTagBuilder().withStatus("MEDIUM").build();
    }
}
