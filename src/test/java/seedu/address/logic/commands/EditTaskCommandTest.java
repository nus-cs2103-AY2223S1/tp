package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TODO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeadlines.getTypicalTaskBookWithDeadlines;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalToDos.getTypicalTaskBookWithToDos;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.DeadlineBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.ToDoBuilder;

class EditTaskCommandTest {

    @Test
    void execute_allFieldsSpecifiedToDo_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBookWithToDos(), new UserPrefs());

        Task editedTask = new ToDoBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), model.getTaskBook(), new UserPrefs());
        expectedModel.setTask(model.getTaskList().get(0), editedTask);
        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_allFieldsSpecifiedDeadline_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBookWithDeadlines(), new UserPrefs());
        Task editedTask = new DeadlineBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), model.getTaskBook(), new UserPrefs());
        expectedModel.setTask(model.getTaskList().get(0), editedTask);
        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_TASK, DESC_TODO);

        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor copyDescriptor = new EditTaskCommand.EditTaskDescriptor(DESC_TODO);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditTaskCommand(INDEX_SECOND_TASK, DESC_TODO));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditTaskCommand(INDEX_FIRST_TASK, DESC_DEADLINE));
    }
}
