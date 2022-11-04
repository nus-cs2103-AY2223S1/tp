package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_ABSENT_GEA1000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DO_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2040;
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

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EditTaskCommand}.
 */
public class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sameFieldsSpecified_failure() {
        // same module and description specified
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(taskToEdit).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_SAME_FIELDS_PROVIDED, taskToEdit);

        assertCommandFailure(editTaskCommand, model, expectedMessage);

        // same module specified
        taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        descriptor = new EditTaskDescriptorBuilder()
            .withModule(taskToEdit.getModule().getModuleCode().moduleCode).build();
        editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        expectedMessage = String.format(EditTaskCommand.MESSAGE_SAME_FIELDS_PROVIDED, taskToEdit);

        assertCommandFailure(editTaskCommand, model, expectedMessage);

        // same description specified
        taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        descriptor = new EditTaskDescriptorBuilder()
            .withDescription(taskToEdit.getDescription().description).build();
        editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        expectedMessage = String.format(EditTaskCommand.MESSAGE_SAME_FIELDS_PROVIDED, taskToEdit);

        assertCommandFailure(editTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_descriptionChanged_success() {
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskToEdit).withTaskDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        expectedModel.replaceTask(expectedModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()),
            editedTask, false);
        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moduleChangedForUnlinkedTask_success() {
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withModule(VALID_MODULE_CS2040).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskToEdit).withModule(VALID_MODULE_CS2040).build();
        expectedModel.replaceTask(expectedModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()),
            editedTask, false);
        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moduleChangedForLinkedTask_success() {
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_LINKED_TASK.getZeroBased());
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withModule(VALID_MODULE_CS2030).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_LINKED_TASK, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskToEdit).withExam(null).withModule(VALID_MODULE_CS2030).build();
        expectedModel.replaceTask(expectedModel.getFilteredTaskList().get(INDEX_LINKED_TASK.getZeroBased()),
            editedTask, false);

        String expectedMessage = MESSAGE_EXAM_UNLINKED
            + String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleSpecified_failure() {
        // module does not exist -> throws error
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withModule(INVALID_MODULE_ABSENT_GEA1000).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_MODULE_NOT_FOUND);


    }

    @Test
    public void execute_duplicateTask_failure() {
        Task secondTask = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription(secondTask.getDescription().description).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editTaskCommand, model, MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_DO_TUTORIAL).build());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task editedTask = new TaskBuilder(taskInFilteredList)
            .withTaskDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        expectedModel.replaceTask(expectedModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()),
            editedTask, false);
        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

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
        // index "size of list + 1" chosen as boundary value for partition [size of list + 1...INT_MAX]
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription(VALID_MODULE_CS2040).build();
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
                new EditTaskDescriptorBuilder().withDescription(VALID_MODULE_CS2040).build());

        String expectedMessage = String.format(
            Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, model.getFilteredTaskList().size() + 1);

        assertCommandFailure(editTaskCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_TASK, DESC_TUTORIAL);

        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor copyDescriptor = new EditTaskCommand.EditTaskDescriptor(DESC_TUTORIAL);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND_TASK, DESC_TUTORIAL)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST_TASK, DESC_LECTURE)));
    }
}
