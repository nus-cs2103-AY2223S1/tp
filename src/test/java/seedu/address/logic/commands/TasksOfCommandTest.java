package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOUR;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_1_DETAILS;

import java.util.function.Predicate;

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

class TasksOfCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new TasksOfCommand();
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
        expectedModel.getTeam().addTask(TASK_1_DETAILS);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_haveTasks_success() {
        commandLine.parseArgs(new String[] {"1"});
        Predicate<Task> predicate = task -> task.checkAssignee(ALICE);
        expectedModel.updateFilteredTaskList(predicate);
        CommandResult expectedResult = new CommandResult(
                String.format(TasksOfCommand.MESSAGE_SUCCESS, 1, ALICE.getName()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_haveNoTasks_success() {
        commandLine.parseArgs(new String[] {"3"});
        Predicate<Task> predicate = task -> task.checkAssignee(CARL);
        expectedModel.updateFilteredTaskList(predicate);
        CommandResult expectedResult = new CommandResult(
                String.format(TasksOfCommand.MESSAGE_SUCCESS, 0, CARL.getName()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        int memberSize = model.getFilteredMemberList().size();
        commandLine.parseArgs(new String[] {String.valueOf(memberSize + 1)});
        String resultString = String.format(TasksOfCommand.MESSAGE_MEMBER_INDEX_TOO_LARGE, INDEX_FOUR.getOneBased());
        assertThrows(CommandException.class, resultString, ()
                -> commandToBeTested.execute(model));
    }
}