package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class TaskDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDelete = model.getFilteredTeamList().get(INDEX_FIRST_TEAM.getZeroBased()).getTask(INDEX_FIRST_TASK.getZeroBased());
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);

        String expectedMessage = String.format(TaskDeleteCommand.MESSAGE_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());
        expectedModel.deleteTask(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);

        assertCommandSuccess(taskDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTeamIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeamList().size() + 1);
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(outOfBoundIndex, INDEX_FIRST_TASK);

        assertCommandFailure(taskDeleteCommand, model, Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTeamList().get(INDEX_FIRST_TEAM.getZeroBased()).getTasks().getSize() + 1);
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(INDEX_FIRST_TEAM, outOfBoundIndex);

        assertCommandFailure(taskDeleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TaskDeleteCommand taskDeleteFirstCommand = new TaskDeleteCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);
        TaskDeleteCommand taskDeleteSecondCommand = new TaskDeleteCommand(INDEX_FIRST_TEAM, INDEX_SECOND_TASK);
        TaskDeleteCommand taskDeleteThirdCommand = new TaskDeleteCommand(INDEX_SECOND_TEAM, INDEX_FIRST_TASK);
        TaskDeleteCommand taskDeleteFourthCommand = new TaskDeleteCommand(INDEX_SECOND_TEAM, INDEX_SECOND_TASK);


        // same object -> returns true
        assertTrue(taskDeleteFirstCommand.equals(taskDeleteFirstCommand));

        // same values -> returns true
        TaskDeleteCommand taskDeleteFirstCommandCopy = new TaskDeleteCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);
        assertTrue(taskDeleteFirstCommand.equals(taskDeleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(taskDeleteFirstCommand.equals(null));

        // different team -> returns false
        assertFalse(taskDeleteFirstCommand.equals(taskDeleteThirdCommand));

        // different task -> returns false
        assertFalse(taskDeleteFirstCommand.equals(taskDeleteSecondCommand));

        // different object -> returns false
        assertFalse(taskDeleteFirstCommand.equals(taskDeleteFourthCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model, Index teamIndex) {
        model.updateFilteredTeamList(p -> false);

        assertTrue(model.getFilteredTeamList().get(teamIndex.getZeroBased()).getTasks().isEmpty());
    }
}
