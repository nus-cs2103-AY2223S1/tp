package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_TWO;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_CODE;
import static seedu.address.testutil.TypicalTasks.TASK_MARKED;

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

class UnmarkCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new UnmarkCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addTask(TASK_MARKED);
        expectedModel.getTeam().addTask(TASK_MARKED);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_marked_success() {
        commandLine.parseArgs(new String[] {"1"});
        Task unmarkedTask = TASK_MARKED.mark(false);
        expectedModel.getTeam().setTask(TASK_MARKED, unmarkedTask);
        CommandResult expectedResult = new CommandResult(
                String.format(UnmarkCommand.MESSAGE_MARK_SUCCESS, unmarkedTask.getName()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_alreadyUnmarked_throwsCommandException() {
        model.getTeam().addTask(TASK_CODE);
        commandLine.parseArgs(new String[] {"2"});
        assertThrows(CommandException.class, UnmarkCommand.MESSAGE_ALREADY_UNMARKED, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_outOfBounds_throwsCommandException() {
        commandLine.parseArgs(new String[] {"2"});
        String expectedResult = String.format(MarkCommand.MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, INDEX_TWO.getOneBased());
        assertThrows(CommandException.class, expectedResult, ()
                -> commandToBeTested.execute(model));
    }
}