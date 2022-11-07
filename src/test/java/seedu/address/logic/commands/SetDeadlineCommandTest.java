package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_TWO;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_1;

import java.time.LocalDateTime;

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

class SetDeadlineCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new SetDeadlineCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());
    @BeforeEach
    public void setUp() {
        model.getTeam().addMember(ALICE);
        expectedModel.getTeam().addMember(ALICE);
        model.getTeam().addTask(TASK_1);
        expectedModel.getTeam().addTask(TASK_1);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                SetDeadlineCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_setNewDeadline_success() {
        commandLine.parseArgs(new String[] {"1", "2023-12-25", "23:59"});
        Task newTask = TASK_1.setDeadline(LocalDateTime.of(2023, 12, 25, 23, 59));
        expectedModel.setTask(TASK_1, newTask);
        CommandResult expectedResult = new CommandResult(
                String.format(SetDeadlineCommand.MESSAGE_SET_DEADLINE_SUCCESS,
                        newTask.getName(), newTask.getDeadlineAsString()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);

    }
    @Test
    public void execute_invalidTaskIndexUnfilteredList_throwsCommandException() {
        commandLine.parseArgs(new String[] {"2", "2023-12-25", "23:59"});
        String commandResultString = String.format(
                SetDeadlineCommand.MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, INDEX_TWO.getOneBased());
        assertThrows(CommandException.class, commandResultString, ()
                -> commandToBeTested.execute(model));
    }
}
