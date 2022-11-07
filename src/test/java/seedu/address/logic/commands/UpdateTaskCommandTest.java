package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.getLocalDate;
import static seedu.address.logic.commands.CommandTestUtil.OVERDUE_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_OIL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateTaskCommand.UpdateTaskDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.UpdateTaskDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateTaskCommand.
 */
public class UpdateTaskCommandTest {

    private final Model model = new ModelManager(new AddressBook(), new UserPrefs(),
            getTypicalTaskList(), getTypicalInventory());
    private final Index indexFirstTask = Index.fromOneBased(1);
    private final Index indexSecondTask = Index.fromOneBased(2);
    private final Index indexFourthTask = Index.fromOneBased(4);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task updatedTask = new TaskBuilder().withTitle("Buy Ginger")
                .withDeadline(getLocalDate("2029-02-10"))
                .withStatus(false).build();
        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder(updatedTask).build();
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(indexFirstTask, descriptor);

        String expectedMessage = String.format(UpdateTaskCommand.MESSAGE_UPDATE_TASK_SUCCESS, updatedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
        expectedModel.setTask(updatedTask, indexFirstTask);

        assertCommandSuccess(updateTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task updatedTask = taskInList.withTitle(VALID_TITLE_OIL).withDeadline(getLocalDate(VALID_DEADLINE))
                .withTags(VALID_TAG_FOOD).build();

        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL)
                .withDeadline(getLocalDate(VALID_DEADLINE)).withTags(VALID_TAG_FOOD).build();
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(UpdateTaskCommand.MESSAGE_UPDATE_TASK_SUCCESS, updatedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
        expectedModel.setTask(updatedTask, indexLastTask);

        assertCommandSuccess(updateTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(indexFirstTask, new UpdateTaskDescriptor());
        Task updatedTask = model.getFilteredTaskList().get(indexFirstTask.getZeroBased());

        String expectedMessage = String.format(UpdateTaskCommand.MESSAGE_UPDATE_TASK_SUCCESS, updatedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());

        assertCommandSuccess(updateTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deadlinePastTodayFieldSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task updatedTask = taskInList.withTitle(VALID_TITLE_OIL).withDeadline(getLocalDate(OVERDUE_DEADLINE))
                .withTags(VALID_TAG_FOOD).build();

        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL)
                .withDeadline(getLocalDate(OVERDUE_DEADLINE)).withTags(VALID_TAG_FOOD).build();
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(UpdateTaskCommand.MESSAGE_UPDATE_TASK_WARNING, updatedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());
        expectedModel.setTask(updatedTask, indexLastTask);

        assertCommandSuccess(updateTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, indexFirstTask);

        Task taskInFilteredList = model.getFilteredTaskList().get(indexFirstTask.getZeroBased());
        Task updatedTask = new TaskBuilder(taskInFilteredList).withTitle(VALID_TITLE_OIL).build();
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(indexFirstTask,
                new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL).build());

        String expectedMessage = String.format(UpdateTaskCommand.MESSAGE_UPDATE_TASK_SUCCESS, updatedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                getTypicalTaskList(), getTypicalInventory());;
        expectedModel.setTask(updatedTask, indexFirstTask);

        assertCommandSuccess(updateTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(indexFirstTask.getZeroBased());
        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder(firstTask).build();
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(indexSecondTask, descriptor);

        assertCommandFailure(updateTaskCommand, model, UpdateTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        showTaskAtIndex(model, indexFirstTask);

        // Update Task in filtered list into a duplicate in task list
        Task taskInList = model.getTaskList().getTaskList().get(indexSecondTask.getZeroBased());
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(indexFirstTask,
                new UpdateTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(updateTaskCommand, model, UpdateTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL).build();
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Update filtered list where index is larger than size of filtered list,
     * but smaller than size of task list
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showTaskAtIndex(model, indexFirstTask);
        Index outOfBoundIndex = indexSecondTask;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(outOfBoundIndex,
                new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL).build());

        assertCommandFailure(updateTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UpdateTaskDescriptor descOil = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL)
                .withDeadline(getLocalDate(VALID_DEADLINE)).withTags(VALID_TAG_URGENT).build();
        UpdateTaskDescriptor descChicken = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_CHICKEN)
                .withDeadline(getLocalDate(VALID_DEADLINE)).withTags(VALID_TAG_FOOD).build();

        final UpdateTaskCommand standardCommand = new UpdateTaskCommand(indexFirstTask, descOil);

        // same values -> returns true
        UpdateTaskDescriptor copyDescriptor = new UpdateTaskDescriptor(descOil);
        UpdateTaskCommand commandWithSameValues = new UpdateTaskCommand(indexFirstTask, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateTaskCommand(indexSecondTask, descOil)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateTaskCommand(indexFirstTask, descChicken)));
    }

}
