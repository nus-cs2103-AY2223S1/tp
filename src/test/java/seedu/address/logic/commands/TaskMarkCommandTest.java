package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_INVALID_TASK;
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

}
