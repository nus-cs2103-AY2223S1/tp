package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_1_DETAILS;
import static seedu.address.testutil.TypicalTasks.TASK_ONLY_ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Task;
import seedu.address.testutil.TaskBuilder;

class AssignTaskRandomlyCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new AssignTaskRandomlyCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addMember(ALICE);
        model.getTeam().addMember(BENSON);
        expectedModel.getTeam().addMember(ALICE);
        expectedModel.getTeam().addMember(BENSON);
        model.getTeam().addTask(TASK_1_DETAILS);
        model.getTeam().addTask(TASK_ONLY_ALICE);
        expectedModel.getTeam().addTask(TASK_1_DETAILS);
        expectedModel.getTeam().addTask(TASK_ONLY_ALICE);
    }
    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }


    @Test
    public void execute_assignOne_success() {
        commandLine.parseArgs("2");
        Task taskCopy = new TaskBuilder(TASK_ONLY_ALICE).build();
        Task assignedTask = taskCopy.addAssignee(BENSON);
        expectedModel.setTask(TASK_ONLY_ALICE, assignedTask);
        CommandResult expectedResult = new CommandResult(
                String.format(AssignTaskRandomlyCommand.MESSAGE_ASSIGN_TASK_SUCCESS,
                        assignedTask.getName(), BENSON.getName()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);

    }

    @Test
    public void execute_allAssigned_throwsCommandException() {
        commandLine.parseArgs("1");
        assertThrows(CommandException.class, AssignTaskRandomlyCommand.MESSAGE_ALL_MEMBERS_ASSIGNED,
                () -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_invalidTaskIndexOutOfBounds_throwsCommandException() {
        int invalidTaskIndex = model.getFilteredTaskList().size() + 1;
        commandLine.parseArgs(String.valueOf(invalidTaskIndex));
        String resultString =String.format(
                AssignTaskRandomlyCommand.MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, invalidTaskIndex);
        assertThrows(CommandException.class, resultString, () -> commandToBeTested.execute(model));
    }

}