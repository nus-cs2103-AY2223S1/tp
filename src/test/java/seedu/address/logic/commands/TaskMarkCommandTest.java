package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_INVALID_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TEAM;
import static seedu.address.testutil.TypicalTasks.PACK;
import static seedu.address.testutil.TypicalTeams.getTypicalAddressBookWithTeams;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class TaskMarkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBookWithTeams(), new UserPrefs());

    @Test
    public void execute_markMarkedTask_failure() {
        Task defaultTask = new TaskBuilder(PACK).build();
        defaultTask.markAsDone();

        TaskMarkCommand taskMarkCommand = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_THIRD_TASK);
        String expectedMessage = String.format(TaskMarkCommand.MESSAGE_ALREADY_MARKED, defaultTask);

        assertCommandFailure(taskMarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        TaskMarkCommand taskMarkCommand = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_INVALID_TASK);

        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(taskMarkCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_invalidTeamIndex_failure() {
        TaskMarkCommand taskMarkCommand = new TaskMarkCommand(INDEX_THIRD_TEAM, INDEX_FIRST_TASK);

        String expectedMessage = Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(taskMarkCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_nullInputUnfilteredList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskMarkCommand(null, null));
    }

    @Test
    public void execute_nullFieldUnfilteredList_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> new TaskMarkCommand(INDEX_FIRST_TEAM, null));

        assertThrows(NullPointerException.class, () -> new TaskMarkCommand(null, INDEX_FIRST_TASK));

    }

    @Test
    public void equals() {
        TaskMarkCommand taskMarkFirstCommand = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);
        TaskMarkCommand taskMarkSecondCommand = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_SECOND_TASK);
        TaskMarkCommand taskMarkThirdCommand = new TaskMarkCommand(INDEX_SECOND_TEAM, INDEX_FIRST_TASK);
        TaskMarkCommand taskMarkFourthCommand = new TaskMarkCommand(INDEX_SECOND_TEAM, INDEX_SECOND_TASK);


        // same object -> returns true
        assertTrue(taskMarkFirstCommand.equals(taskMarkFirstCommand));

        // same values -> returns true
        TaskMarkCommand taskMarkFirstCommandCopy = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);
        assertTrue(taskMarkFirstCommand.equals(taskMarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskMarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(taskMarkFirstCommand.equals(null));

        // different team -> returns false
        assertFalse(taskMarkFirstCommand.equals(taskMarkThirdCommand));

        // different task -> returns false
        assertFalse(taskMarkFirstCommand.equals(taskMarkSecondCommand));

        // different object -> returns false
        assertFalse(taskMarkFirstCommand.equals(taskMarkFourthCommand));
    }

}
