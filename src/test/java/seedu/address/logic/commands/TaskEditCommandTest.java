package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalTasks.COOK;
import static seedu.address.testutil.TypicalTasks.STUDY;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

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
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class TaskEditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder(COOK).build();
        TaskEditCommand taskEditCommand = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                editedTask.getName(), editedTask.getDeadline().get());

        String expectedMessage = String.format(TaskEditCommand.MESSAGE_SUCCESS,
                editedTask.getName(), editedTask.getDeadline().get());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.editTask(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                editedTask.getName(), editedTask.getDeadline().get());

        assertCommandSuccess(taskEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_taskNameFieldSpecifiedUnfilteredList_success() {

        Task editedTask = new TaskBuilder(COOK).build();
        TaskEditCommand taskEditCommand = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                editedTask.getName(), null);

        String expectedMessage = String.format(TaskEditCommand.MESSAGE_SUCCESS, editedTask.getName(), "");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.editTask(INDEX_FIRST_TEAM, INDEX_FIRST_TASK, editedTask.getName(), null);

        assertCommandSuccess(taskEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deadlineFieldSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder(COOK).build();
        TaskEditCommand taskEditCommand = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                null, editedTask.getDeadline().get());
        Task oldTask = model.getFilteredTeamList().get(INDEX_FIRST_TEAM.getZeroBased())
                .getTask(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(TaskEditCommand.MESSAGE_SUCCESS,
                oldTask.getName(), editedTask.getDeadline().get());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.editTask(INDEX_FIRST_TEAM, INDEX_FIRST_TASK, null, editedTask.getDeadline().get());

        assertCommandSuccess(taskEditCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Task editedTask = new TaskBuilder(STUDY).build();
        TaskEditCommand taskEditCommand = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_SECOND_TASK,
                editedTask.getName(), editedTask.getDeadline().get());

        assertCommandFailure(taskEditCommand, model, TaskEditCommand.MESSAGE_DUPLICATE_TASK);
    }


    @Test
    public void execute_invalidTeamIndexUnfilteredList_throwsCommandException() {
        Task editedTask = new TaskBuilder(COOK).build();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeamList().size() + 1);
        TaskEditCommand taskEditCommand = new TaskEditCommand(outOfBoundIndex, INDEX_FIRST_TASK,
                editedTask.getName(), editedTask.getDeadline().get());

        assertCommandFailure(taskEditCommand, model, Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_throwsCommandException() {
        Task editedTask = new TaskBuilder(COOK).build();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeamList()
                .get(INDEX_FIRST_TEAM.getZeroBased()).getTasks().getSize() + 1);
        TaskEditCommand taskEditCommand = new TaskEditCommand(INDEX_FIRST_TEAM, outOfBoundIndex,
                editedTask.getName(), editedTask.getDeadline().get());

        assertCommandFailure(taskEditCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        Task editedTask = new TaskBuilder(COOK).build();
        Task editedTask1 = new TaskBuilder(STUDY).build();
        TaskEditCommand taskEditCommand1 = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                editedTask.getName(), editedTask.getDeadline().get());
        TaskEditCommand taskEditCommand2 = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                editedTask.getName(), editedTask1.getDeadline().get());
        TaskEditCommand taskEditCommand3 = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                editedTask1.getName(), editedTask.getDeadline().get());
        TaskEditCommand taskEditCommand4 = new TaskEditCommand(INDEX_SECOND_TEAM, INDEX_FIRST_TASK,
                editedTask.getName(), editedTask.getDeadline().get());
        TaskEditCommand taskEditCommand5 = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_SECOND_TASK,
                editedTask.getName(), editedTask.getDeadline().get());


        // same values -> returns true
        TaskEditCommand commandWithSameValues = new TaskEditCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK,
                editedTask.getName(), editedTask.getDeadline().get());
        assertTrue(taskEditCommand1.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(taskEditCommand1.equals(taskEditCommand1));

        // null -> returns false
        assertFalse(taskEditCommand1.equals(null));

        // different types -> returns false
        assertFalse(taskEditCommand1.equals(new ClearCommand()));

        // different taskName -> returns false
        assertFalse(taskEditCommand1.equals(taskEditCommand3));

        // different deadline -> returns false
        assertFalse(taskEditCommand1.equals(taskEditCommand2));

        // different teamIndex -> returns false
        assertFalse(taskEditCommand1.equals(taskEditCommand4));

        // different taskIndex -> returns false
        assertFalse(taskEditCommand1.equals(taskEditCommand5));
    }

}
