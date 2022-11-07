package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_INVALID_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TEAM;
import static seedu.address.testutil.TypicalTasks.PACK;
import static seedu.address.testutil.TypicalTasks.REVIEW;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.model.team.Team;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TeamBuilder;

public class TaskUnmarkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());

    @Test
    public void execute_unmarkUnmarkedTask_failure() {
        Task defaultTask = new TaskBuilder(REVIEW).build();
        Team defaultTeam = new TeamBuilder().withTasks(defaultTask).build();
        Task task = defaultTeam.getTask(0);
        task.markAsNotDone();

        TaskUnmarkCommand taskUnmarkCommand = new TaskUnmarkCommand(INDEX_FIRST_TEAM, INDEX_SECOND_TASK);
        String expectedMessage = String.format(TaskUnmarkCommand.MESSAGE_ALREADY_UNMARKED, task);

        assertCommandFailure(taskUnmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_unmarkMarkedTask_success() {
        Task defaultTask = new TaskBuilder(PACK).build();
        TaskUnmarkCommand taskUnmarkCommand = new TaskUnmarkCommand(INDEX_FIRST_TEAM, INDEX_THIRD_TASK);
        defaultTask.markAsNotDone();

        String expectedMessage = String.format(TaskUnmarkCommand.MESSAGE_SUCCESS, defaultTask);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(taskUnmarkCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidTaskIndex_failure() {
        TaskUnmarkCommand taskUnmarkCommand = new TaskUnmarkCommand(INDEX_FIRST_TEAM, INDEX_INVALID_TASK);

        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(taskUnmarkCommand, expectedModel, expectedMessage);
    }


    @Test
    public void execute_invalidTeamIndex_failure() {
        TaskUnmarkCommand taskUnmarkCommand = new TaskUnmarkCommand(INDEX_THIRD_TEAM, INDEX_FIRST_TASK);

        String expectedMessage = Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(taskUnmarkCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_nullInputUnfilteredList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskUnmarkCommand(null, null));
    }

    @Test
    public void execute_nullFieldUnfilteredList_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> new TaskUnmarkCommand(INDEX_FIRST_TEAM, null));

        assertThrows(NullPointerException.class, () -> new TaskUnmarkCommand(null, INDEX_FIRST_TASK));

    }

    @Test
    public void equals() {
        TaskUnmarkCommand taskUnmarkFirstCommand = new TaskUnmarkCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);
        TaskUnmarkCommand taskUnmarkSecondCommand = new TaskUnmarkCommand(INDEX_FIRST_TEAM, INDEX_SECOND_TASK);
        TaskUnmarkCommand taskUnmarkThirdCommand = new TaskUnmarkCommand(INDEX_SECOND_TEAM, INDEX_FIRST_TASK);
        TaskUnmarkCommand taskUnmarkFourthCommand = new TaskUnmarkCommand(INDEX_SECOND_TEAM, INDEX_SECOND_TASK);


        // same object -> returns true
        assertTrue(taskUnmarkFirstCommand.equals(taskUnmarkFirstCommand));

        // same values -> returns true
        TaskUnmarkCommand taskUnmarkFirstCommandCopy = new TaskUnmarkCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);
        assertTrue(taskUnmarkFirstCommand.equals(taskUnmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(null));

        // different team -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(taskUnmarkThirdCommand));

        // different task -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(taskUnmarkSecondCommand));

        // different object -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(taskUnmarkFourthCommand));
    }

}
