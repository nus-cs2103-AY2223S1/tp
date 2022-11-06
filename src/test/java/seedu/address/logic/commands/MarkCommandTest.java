package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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

class MarkCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new MarkCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addTask(TASK_CODE);
        expectedModel.getTeam().addTask(TASK_CODE);
    }

    @Test
    public void execute_notMarked_success() {
        commandLine.parseArgs(new String[] {"1"});
        Task markedTask = TASK_CODE.mark(true);
        expectedModel.getTeam().setTask(TASK_CODE, markedTask);
        CommandResult expectedResult = new CommandResult(
                String.format(MarkCommand.MESSAGE_MARK_SUCCESS, markedTask.getName()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_alreadyMarked_throwsCommandException() {
        model.getTeam().addTask(TASK_MARKED);
        commandLine.parseArgs(new String[] {"2"});
        assertThrows(CommandException.class, MarkCommand.MESSAGE_ALREADY_MARKED, ()
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