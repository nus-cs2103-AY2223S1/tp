package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_QUIZ;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPORT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class MarkTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_alreadyMarkedUnfilteredList_failure() {
        Task secondTask = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(secondTask).build();
        MarkTaskCommand markCommand = new MarkTaskCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(markCommand, model, MarkTaskCommand.MESSAGE_ALREADY_MARKED);
    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        MarkTaskCommand markCommand = new MarkTaskCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(markCommand, model, MarkTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        // mark task in filtered list into a duplicate in address book
        Task taskInList = model.getAddressBook().getTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        MarkTaskCommand markCommand = new MarkTaskCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(markCommand, model, MarkTaskCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withCompletionStatus(true).build();
        MarkTaskCommand markCommand = new MarkTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        MarkTaskCommand markCommand = new MarkTaskCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withCompletionStatus(true).build());

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MarkTaskCommand standardCommand = new MarkTaskCommand(INDEX_FIRST_TASK, DESC_QUIZ);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_QUIZ);
        MarkTaskCommand commandWithSameValues = new MarkTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkTaskCommand(INDEX_SECOND_TASK, DESC_QUIZ)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new MarkTaskCommand(INDEX_FIRST_TASK, DESC_REPORT)));
    }
}
