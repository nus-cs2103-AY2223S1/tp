package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_CODE;
import static seedu.address.testutil.TypicalTasks.TASK_REVIEW;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.parser.Order;
import seedu.address.logic.parser.OrderConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class SortTaskCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new SortTaskCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Order.class, new OrderConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addTask(TASK_CODE);
        model.getTeam().addTask(TASK_1);
        model.getTeam().addTask(TASK_REVIEW);
        expectedModel.getTeam().addTask(TASK_CODE);
        expectedModel.getTeam().addTask(TASK_1);
        expectedModel.getTeam().addTask(TASK_REVIEW);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                SortTaskCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_unsortedMembersToAscending_success() {
        commandLine.parseArgs(new String[] {"asc"});
        expectedModel.getTeam().sortTasks((t1, t2) -> t1.getName().toString().compareTo(t2.getName().toString()));
        assertCommandSuccess(commandToBeTested, model, SortTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void execute_unsortedMembersToDescending_success() {
        commandLine.parseArgs(new String[] {"dsc"});
        expectedModel.getTeam().sortTasks((t1, t2) -> t2.getName().toString().compareTo(t1.getName().toString()));
        assertCommandSuccess(commandToBeTested, model, SortTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void execute_sortedAscendingMembersToUnsorted_success() {
        model.getTeam().sortTasks((t1, t2) -> t1.getName().toString().compareTo(t2.getName().toString()));
        commandLine.parseArgs(new String[] {"res"});
        assertCommandSuccess(commandToBeTested, model, SortTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
