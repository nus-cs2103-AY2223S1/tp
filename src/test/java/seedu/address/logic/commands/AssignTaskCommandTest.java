package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_1_DETAILS;
import static seedu.address.testutil.TypicalTasks.TASK_2;
import static seedu.address.testutil.TypicalTasks.TASK_2_NO_ASSIGNEE;
import static seedu.address.testutil.TypicalTasks.TASK_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Task;
import seedu.address.testutil.TaskBuilder;

class AssignTaskCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new AssignTaskCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addMember(ALICE);
        model.getTeam().addMember(BENSON);
        model.getTeam().addMember(CARL);
        expectedModel.getTeam().addMember(ALICE);
        expectedModel.getTeam().addMember(BENSON);
        expectedModel.getTeam().addMember(CARL);
        model.getTeam().addTask(TASK_1_DETAILS);
        model.getTeam().addTask(TASK_2_NO_ASSIGNEE);
        expectedModel.getTeam().addTask(TASK_1_DETAILS);
        expectedModel.getTeam().addTask(TASK_2_NO_ASSIGNEE);
    }

    @Test
    public void execute_assignOne_success() {
        commandLine.parseArgs("2", FLAG_ASSIGNEE_STR, "1");
        Task taskCopy = new TaskBuilder(TASK_2_NO_ASSIGNEE).build();
        Task assignedTask = taskCopy.addAssignee(ALICE);
        expectedModel.setTask(TASK_2_NO_ASSIGNEE, assignedTask);
        CommandResult expectedResult = new CommandResult(
                String.format(AssignTaskCommand.MESSAGE_ASSIGN_TASK_SUCCESS, assignedTask));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_assignMultiple_success() {
        commandLine.parseArgs("2", FLAG_ASSIGNEE_STR, "2","3");
        Task taskCopy = new TaskBuilder(TASK_2_NO_ASSIGNEE).build();
        Task assignedTask = taskCopy.addAssignee(BENSON).addAssignee(CARL);
        expectedModel.setTask(TASK_2_NO_ASSIGNEE, assignedTask);
        CommandResult expectedResult = new CommandResult(
                String.format(AssignTaskCommand.MESSAGE_ASSIGN_TASK_SUCCESS, assignedTask));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_alreadyAssigned_throwsCommandException() {
        commandLine.parseArgs("1", FLAG_ASSIGNEE_STR, "1");
        String resultString = String.format(AssignTaskCommand.MESSAGE_DUPLICATE_ASSIGNMENT, ALICE.getName());
        assertThrows(CommandException.class, resultString, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_invalidTaskIndexOutOfBounds_throwsCommandException() {
        int invalidTaskIndex = model.getFilteredTaskList().size() + 1;
        commandLine.parseArgs(String.valueOf(invalidTaskIndex), FLAG_ASSIGNEE_STR, "1");
        String resultString = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertThrows(CommandException.class, resultString, () -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_invalidAssigneeIndexOutOfBounds_throwsCommandException() {
        int invalidMemberIndex = model.getFilteredMemberList().size() + 1;
        commandLine.parseArgs("1", FLAG_ASSIGNEE_STR, String.valueOf(invalidMemberIndex));
        String resultString = AssignTaskCommand.MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS;
        assertThrows(CommandException.class, resultString, () -> commandToBeTested.execute(model));
    }


}
