package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_TASK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND_TASK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_EXAM_UNLINKED;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_LINKED_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;

public class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test // [same fields specified]
    public void execute_sameFieldsSpecifiedUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask)
            .build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, new EditTaskCommand.EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_SAME_FIELDS_PROVIDED, editedTask);

        assertCommandFailure(editTaskCommand, model, expectedMessage);
    }

    @Test // [a different task description specified]
    public void execute_descriptionChangedUnfilteredList_success() {
        Task firstTask = model.getFilteredTaskList().get(0);
        Task editedTask = new TaskBuilder(firstTask).withDescription("first task").build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.replaceTask(model.getFilteredTaskList().get(0), editedTask, false);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test // [a different module specified for an unlinked task]
    public void execute_moduleChangedForUnlinkedTask_success() {
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withModule("cs2100").build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(firstTask).withModule("cs2100").build();

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.replaceTask(model.getFilteredTaskList().get(0), editedTask, false);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test // [a different module specified for a linked task]
    public void execute_moduleChangedForLinkedTask_success() {
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withModule("cs2030").build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_LINKED_TASK, descriptor);

        Task linkedTask = model.getFilteredTaskList().get(INDEX_LINKED_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(linkedTask).withExam(null).withModule("cs2030").build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.replaceTask(linkedTask, editedTask, false);

        String expectedMessage = MESSAGE_EXAM_UNLINKED
            + String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test // [invalid module specified]
    public void execute_invalidModuleSpecified_failure() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withModule("gea1000").build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = Messages.MESSAGE_MODULE_NOT_FOUND;

        assertCommandFailure(editTaskCommand, model, expectedMessage);
    }

    @Test // [duplicate task]
    public void execute_duplicateTask_failure() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription("task e").build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = MESSAGE_DUPLICATE_TASK;

        assertCommandFailure(editTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withDescription("first task").build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder().withDescription("first task").build());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.replaceTask(model.getFilteredTaskList().get(0), editedTask, false);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        // edit task in filtered list into a duplicate in address book
        Task taskInList = model.getAddressBook().getTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(editTaskCommand, model, MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription("cs2030").build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        String expectedMessage = String.format(
            Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, model.getFilteredTaskList().size() + 1);

        assertCommandFailure(editTaskCommand, model, expectedMessage);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withDescription("cs2030").build());

        String expectedMessage = String.format(
            Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, model.getFilteredTaskList().size() + 1);

        assertCommandFailure(editTaskCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_TASK, DESC_FIRST_TASK);

        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor copyDescriptor = new EditTaskCommand.EditTaskDescriptor(DESC_FIRST_TASK);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND_TASK, DESC_FIRST_TASK)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST_TASK, DESC_SECOND_TASK)));
    }
}
