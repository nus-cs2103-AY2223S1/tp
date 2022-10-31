package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TEAM;
import static seedu.address.testutil.TypicalTasks.REVIEW;
import static seedu.address.testutil.TypicalTasks.STUDY;
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

//    @Test
//    public void execute_unmarkMarkedTask_success(){
//        Task defaultTask = new TaskBuilder(REVIEW).build();
//        Team defaultTeam = new TeamBuilder().withTasks(defaultTask).build();
//        Task task = defaultTeam.getTask(0);
//        task.markAsNotDone();
//        TaskMarkCommand taskMarkCommand = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_SECOND_TASK);
//
//        String expectedMessage = String.format(TaskMarkCommand.MESSAGE_SUCCESS, task);
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//
//        expectedModel.markTask(INDEX_FIRST_TEAM, INDEX_SECOND_TASK);
//        assertCommandSuccess(taskMarkCommand, model, expectedMessage, expectedModel);
//    }

    @Test
    public void execute_markUnmarkedTask_success(){
        Task defaultTask = new TaskBuilder(STUDY).build();
        Team defaultTeam = new TeamBuilder().withTasks(defaultTask).build();
        Task task = defaultTeam.getTask(0);
        task.markAsDone();
        TaskMarkCommand taskMarkCommand = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);

        String expectedMessage = String.format(TaskMarkCommand.MESSAGE_SUCCESS, task);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.markTask(INDEX_FIRST_TEAM, INDEX_FIRST_TASK);
        assertCommandSuccess(taskMarkCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidTaskIndex_failure(){
        TaskMarkCommand taskMarkCommand = new TaskMarkCommand(INDEX_FIRST_TEAM, INDEX_THIRD_TASK);

        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(taskMarkCommand, expectedModel, expectedMessage);
    }


    @Test
    public void execute_invalidTeamIndex_failure(){
        TaskMarkCommand taskMarkCommand = new TaskMarkCommand(INDEX_THIRD_TEAM, INDEX_FIRST_TASK);

        String expectedMessage = Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandFailure(taskMarkCommand, expectedModel, expectedMessage);
    }

}
